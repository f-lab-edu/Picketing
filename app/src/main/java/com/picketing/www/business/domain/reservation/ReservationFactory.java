package com.picketing.www.business.domain.reservation;

import static com.picketing.www.presentation.dto.response.reservation.MakeReservationResponse.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

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

	public ReservationIdResponse convertReservationId(Reservation reservation) {
		return ReservationIdResponse.builder()
			.reservationId(reservation.getId())
			.build();
	}
}
