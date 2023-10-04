package com.picketing.www.presentation.dto.response.show.seat;

import java.util.List;

import lombok.Builder;

@Builder
public record RemainingSeatsResponse(
	List<RemainingSeatDetail> remainSeats
) {

	@Builder
	public record RemainingSeatDetail(
		String seatGrade,
		int remainCnt
	) {
	}
}
