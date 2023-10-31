package com.picketing.www.business.service.reservation;

import static com.picketing.www.presentation.dto.request.reservation.ReservationRequest.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.User;
import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.business.domain.reservation.ReservationFactory;
import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.business.service.user.UserService;
import com.picketing.www.persistence.repository.reservation.ReservationRepository;
import com.picketing.www.presentation.dto.request.reservation.ReservationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ScheduledShowSeatService scheduledShowSeatService;

	private final UserService userService;

	private final ReservationRepository reservationRepository;

	private final ReservationFactory reservationFactory;

	private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

	@Transactional
	public List<Reservation> makeReservations(Show show, ReservationRequest request) {

		User user = userService.get(request.userId());

		LocalDateTime showTime = request.showTime();

		List<ReservationSeatRequest> seats = request.reservationSeatRequests();

		return makeReservations(user, show, showTime, seats);
	}

	@Transactional
	public List<Reservation> makeReservations(User user, Show show, LocalDateTime showTime,
		List<ReservationSeatRequest> seats) {

		// 예약 정보를 가지고 오고 -> 추가하는 과정에서 동시성 문제가 발생
		List<List<Reservation>> reservationList = seats.stream()
			.map(
				seat -> reservationRepository.findByShowSeatWithPessimisticLock(
					scheduledShowSeatService.getScheduledShowSeat(show, showTime, seat.seatGrade()))
			).toList();

		Map<SeatGrade, Integer> reservedSeatCountMap = new ConcurrentHashMap<>();
		reservationList.forEach((reservation -> {
			Reservation current = reservation.get(0);
			SeatGrade currentSeat = reservationFactory.convertShowSeatGradeByReservation(current);
			int reservedCount = reservation.size();
			reservedSeatCountMap.put(currentSeat, reservedCount);
		}));

		if (!isBookable(reservedSeatCountMap, seats)) {
			throw new CustomException(ErrorCode.ALREADY_RESERVED);
		}

		List<Reservation> reservations = seats
			.stream()
			.flatMap(seatRequest -> makeReservationPerCount(user, show, showTime, seatRequest).stream())
			.collect(Collectors.toList());

		return reservationRepository.saveAll(reservations);
	}

	private boolean isBookable(Map<SeatGrade, Integer> map, List<ReservationSeatRequest> seatRequestList) {
		return seatRequestList.stream()
			.allMatch(seatRequest -> {
				SeatGrade currentSeatGrade = seatRequest.seatGrade();
				Integer purchaseCount = seatRequest.count();
				Integer reservedCount = map.get(currentSeatGrade);

				return ((currentSeatGrade.getCount() - reservedCount) - purchaseCount >= 0);
			});
	}

	private List<Reservation> makeReservationPerCount(User user, Show show, LocalDateTime showTime,
		ReservationSeatRequest request) {
		return IntStream.range(0, request.count())
			.mapToObj(i -> reservationFactory.convertSeatToReservation(user,
				scheduledShowSeatService.getScheduledShowSeat(show, showTime, request.seatGrade())
			))
			.collect(Collectors.toList());
	}

	public Long countReservationsByShowSeat(ScheduledShowSeat scheduledShowSeat) {
		return reservationRepository.countReservationsByShowSeat(scheduledShowSeat);
	}

	public List<Reservation> getReservationsByShowSeat(ScheduledShowSeat scheduledShowSeat) {
		return reservationRepository.findAllByShowSeat(scheduledShowSeat);
	}
}
