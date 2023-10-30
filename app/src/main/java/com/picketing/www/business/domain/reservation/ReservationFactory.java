package com.picketing.www.business.domain.reservation;

import static com.picketing.www.presentation.dto.response.reservation.MakeReservationResponse.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.picketing.www.business.domain.User;
import com.picketing.www.presentation.dto.response.reservation.MakeReservationResponse;

@Component
public class ReservationFactory {

	public MakeReservationResponse convertReservationToResponse(List<Reservation> reservationList) {
		return builder()
			.reservationList(
				reservationList.stream()
					.map(this::convertReservationId)
					.collect(Collectors.toList())
			)
			.build();
	}

	private ReservationIdResponse convertReservationId(Reservation reservation) {
		return ReservationIdResponse.builder()
			.id(reservation.getId())
			.build();
	}

	public Reservation convertSeatToReservation(User user, ScheduledShowSeat showSeat) {
		return Reservation.builder()
			.user(user)
			.showSeat(showSeat)
			.build();
	}

	public ScheduledShowSeat convertShowSeatByReservation(Reservation reservation) {
		return reservation.getShowSeat();
	}
}
