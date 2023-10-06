package com.picketing.www.presentation.dto.request.reservation;

import java.util.List;

import com.picketing.www.business.domain.show.seat.SeatGrade;

import lombok.Builder;

@Builder
public record ReservationRequest(
	Long showId,

	Long userId,

	String showTime,

	List<ReservationSeatRequest> seatGradeList
) {

	@Builder
	public record ReservationSeatRequest(
		SeatGrade seatGrade,
		int count
	) {
	}
}
