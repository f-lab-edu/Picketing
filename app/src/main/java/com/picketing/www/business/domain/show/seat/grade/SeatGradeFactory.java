package com.picketing.www.business.domain.show.seat.grade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.response.show.seat.grade.RemainingSeatCountResponse;

@Component
public class SeatGradeFactory {
	public List<RemainingSeatCountResponse> toRemainingSeatCountResponses(List<SeatGrade> seatGrades) {
		return seatGrades.stream()
			.map(seatGrade -> {
				return new RemainingSeatCountResponse(seatGrade.getId(), seatGrade.getName(),
					seatGrade.getRemainingSeatCount());
			})
			.toList();
	}
}
