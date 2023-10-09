package com.picketing.www.presentation.dto.response.show.seat;

import lombok.Builder;

@Builder
public record SeatGradeResponse(
	String seatGrade,

	int totalCount,
	Long price
) {
}
