package com.picketing.www.business.domain.entertainment.seat;

import static com.picketing.www.presentation.dto.response.entertainment.EntertainmentSeatGradeResponse.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.request.seat.SaveSeatGradeRequest;
import com.picketing.www.presentation.dto.response.entertainment.EntertainmentSeatGradeResponse;

@Component
public class SeatGradeFactory {

	public EntertainmentSeatGradeResponse convertSeatGradeToResponse(List<SeatGrade> seatGrade) {
		return new EntertainmentSeatGradeResponse(
			seatGrade.stream()
				.map(this::convertSeatGradeToDto)
				.collect(Collectors.toList())
		);
	}

	public ShowSeatGradeResponseDto convertSeatGradeToDto(SeatGrade seatGrade) {
		return ShowSeatGradeResponseDto.builder()
			.showId(seatGrade.getSeat().getShow().getId())
			.seatGradeName(seatGrade.getName())
			.price(seatGrade.getPrice())
			.build();
	}

	public SeatGrade createSeatGrade(SaveSeatGradeRequest saveSeatGradeRequest) {
		return SeatGrade.builder()
			.name(saveSeatGradeRequest.name())
			.seat(saveSeatGradeRequest.seat())
			.price(saveSeatGradeRequest.price())
			.build();
	}
}
