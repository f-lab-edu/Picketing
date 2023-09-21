package com.picketing.www.business.domain.show.seatgrade;

import static com.picketing.www.presentation.dto.response.show.ShowSeatGradeResponse.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.response.show.ShowSeatGradeResponse;

@Component
public class SeatGradeFactory {

	public ShowSeatGradeResponse convertSeatGradeToResponse(List<SeatGrade> seatGrade) {
		return new ShowSeatGradeResponse(
			seatGrade.stream()
				.map(this::convertSeatGradeToDto)
				.collect(Collectors.toList())
		);
	}

	public ShowSeatGradeResponseDto convertSeatGradeToDto(SeatGrade seatGrade) {
		return ShowSeatGradeResponseDto.builder()
			.showId(seatGrade.getShow().getId())
			.seatGradeName(seatGrade.getName())
			.price(seatGrade.getPrice())
			.build();
	}
}
