package com.picketing.www.presentation.dto.response.seatGrade;

import java.util.List;

public record SeatGradeCountsResponse(
	List<SeatGradeCountResponse> seatGrades
) {
}
