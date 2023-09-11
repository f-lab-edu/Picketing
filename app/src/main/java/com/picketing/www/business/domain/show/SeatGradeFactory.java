package com.picketing.www.business.domain.show;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.ShowSeatGradePersist;

@Component
public class SeatGradeFactory {
	public ShowSeatGrade create(ShowSeatGradePersist seatGradePersist) {
		return ShowSeatGrade.builder()
			.id(seatGradePersist.id())
			.name(seatGradePersist.name())
			.build();
	}
}
