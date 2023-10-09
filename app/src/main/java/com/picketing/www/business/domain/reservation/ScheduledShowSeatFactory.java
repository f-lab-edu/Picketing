package com.picketing.www.business.domain.reservation;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.response.show.seat.ScheduledShowSeatGradeResponse;

@Component
public class ScheduledShowSeatFactory {

	public ScheduledShowSeatGradeResponse convertShowSeat(ScheduledShowSeat showSeat) {
		return ScheduledShowSeatGradeResponse.builder()
			.show(showSeat.getShow())
			.seatGrade(showSeat.getSeatGrade())
			.showTime(showSeat.getShowDateTime())
			.build();
	}
}
