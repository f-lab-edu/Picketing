package com.picketing.www.presentation.dto.response.show;

import java.util.List;

import lombok.Builder;

@Builder
public record ShowSeatGradeResponse(
	List<ShowSeatGradeResponseDto> seatGradeList
) {

	public ShowSeatGradeResponse(List<ShowSeatGradeResponseDto> seatGradeList) {
		this.seatGradeList = seatGradeList;
	}

	@Builder
	public record ShowSeatGradeResponseDto(
		String seatGradeName,
		Long price
	) {
	}
}
