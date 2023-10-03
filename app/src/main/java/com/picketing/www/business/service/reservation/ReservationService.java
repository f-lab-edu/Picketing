package com.picketing.www.business.service.reservation;

import static com.picketing.www.presentation.dto.request.reservation.ReservationRequest.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.User;
import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.business.domain.reservation.ReservationFactory;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.business.service.show.ShowService;
import com.picketing.www.business.service.user.UserService;
import com.picketing.www.persistence.repository.reservation.ReservationRepository;
import com.picketing.www.presentation.dto.request.reservation.ReservationRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ShowService showService;

	private final ScheduledShowSeatService scheduledShowSeatService;

	private final UserService userService;

	private final ReservationRepository reservationRepository;

	private final ReservationFactory reservationFactory;

	@Transactional
	public List<Reservation> makeReservations(ReservationRequest request) {
		// TODO 이후 세션에서 userId 가지고 올 수 있도록 수정 필요
		User user = userService.get(request.userId());

		Show show = showService.getShowById(request.showId());

		String showTime = request.showTime();

		// 각 좌석이 구매 가능한지 확인
		if (checkAvailable(show, showTime, request.reservationSeatRequests())) {
			throw new CustomException(ErrorCode.ALREADY_RESERVED);
		}

		List<Reservation> reservations = request.reservationSeatRequests()
			.stream()
			.map(r -> reservationFactory.convertToReservation(user,
					scheduledShowSeatService.getScheduledShowSeat(show, showTime, SeatGrade.valueOf(r.seatGrade()))
				)
			)
			.toList();

		return reservationRepository.saveAll(reservations);
	}

	private boolean checkAvailable(Show show, String showTime, List<ReservationSeatRequest> seatRequestList) {
		return seatRequestList.stream()
			.allMatch(s -> {
				int count = s.count();
				SeatGrade currentSeatGrade = SeatGrade.valueOf(s.seatGrade());

				long reservedCount = reservationRepository.countByShowSeat(
					scheduledShowSeatService.getScheduledShowSeat(show, showTime, currentSeatGrade));

				return reservedCount < count;
			});
	}
}
