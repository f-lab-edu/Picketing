package com.picketing.www.business.domain.show.seatgrade;

import static com.picketing.www.presentation.dto.response.show.ShowSeatPriceResponse.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.response.show.ShowSeatPriceResponse;

@Component
public class SeatGradeFactory {

	public ShowSeatPriceResponse findResponse(List<SeatGrade> seatGrade) {
		return new ShowSeatPriceResponse(
			seatGrade.stream()
				.map(this::findSeatGradeResponse)
				.collect(Collectors.toList())
		);
	}

	public ShowSeatBasicPriceResponseDto findSeatGradeResponse(SeatGrade seatGrade) {
		return ShowSeatBasicPriceResponseDto.builder()
			.showId(seatGrade.getShow().getId())
			.seatGradeName(seatGrade.getName())
			.price(seatGrade.getPrice())
			.build();
	}
}
