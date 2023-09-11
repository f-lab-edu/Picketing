package com.picketing.www.presentation.dto.response.seatGrade;

public record SeatGradeCountResponse(
	Long id,
	String name,
	Long remainingSeatCount
) {
}
