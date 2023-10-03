package com.picketing.www.presentation.dto.response.reservation;

import java.util.List;

import lombok.Builder;

@Builder
public record MakeReservationResponse(
	List<ReservationIdResponse> reservationList
) {

	@Builder
	public record ReservationIdResponse(
		Long reservationId
	) {
	}
}
