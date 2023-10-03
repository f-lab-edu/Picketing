package com.picketing.www.presentation.dto.request.reservation;

import java.util.List;

import lombok.Builder;

@Builder
public record ReservationRequest(
	Long showId,

	List<ReservationSeatRequest> reservationSeatRequests
) {

	@Builder
	public record ReservationSeatRequest(
		String seatGrade,

		int count
	) {
	}
}
