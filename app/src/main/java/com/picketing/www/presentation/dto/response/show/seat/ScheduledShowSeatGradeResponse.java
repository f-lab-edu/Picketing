package com.picketing.www.presentation.dto.response.show.seat;

import java.time.LocalDateTime;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seat.SeatGrade;

import lombok.Builder;

@Builder
public record ScheduledShowSeatGradeResponse(
	Show show,

	LocalDateTime showTime,

	SeatGrade seatGrade
) {
}
