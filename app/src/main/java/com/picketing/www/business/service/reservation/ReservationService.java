package com.picketing.www.business.service.reservation;

import static com.picketing.www.presentation.dto.request.reservation.ReservationRequest.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

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

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ScheduledShowSeatService scheduledShowSeatService;

	private final UserService userService;

	private final ReservationRepository reservationRepository;

	private final ReservationFactory reservationFactory;

	public List<Reservation> makeReservations(Show show, ReservationRequest request) {

		User user = userService.get(request.userId());

		LocalDateTime showTime = request.showTime();

		List<ReservationSeatRequest> seats = request.seatGradeList();

		return makeReservations(user, show, showTime, seats);
	}

	@Transactional
	public List<Reservation> makeReservations(User user, Show show, LocalDateTime showTime,
		List<ReservationSeatRequest> seats) {

		if (!isBookable(show, showTime, seats)) {
			throw new CustomException(ErrorCode.ALREADY_RESERVED);
		}

		List<Reservation> reservations = seats
			.stream()
			.flatMap(seatRequest -> makeReservationPerCount(user, show, showTime, seatRequest).stream())
			.collect(Collectors.toList());

		return reservationRepository.saveAll(reservations);
	}

	private boolean isBookable(Show show, LocalDateTime showTime, List<ReservationSeatRequest> seatRequestList) {
		return seatRequestList.stream()
			.allMatch(seatRequest -> {
				SeatGrade currentSeatGrade = seatRequest.seatGrade();
				long reservedCount = countReservationsByShowSeat(
					scheduledShowSeatService.getScheduledShowSeat(show, showTime,
						currentSeatGrade));

				return ((currentSeatGrade.getCount() - reservedCount) - seatRequest.count()) >= 0;
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
