package com.picketing.www.business.domain.show;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.SeatGradePersist;

@Component
public class SeatGradeFactory {
	public SeatGrade create(SeatGradePersist seatGradePersist) {
		return SeatGrade.builder()
			.id(seatGradePersist.id())
			.name(seatGradePersist.name())
			.price(seatGradePersist.price())
			.showId(seatGradePersist.showId())
			.build();
	}
}
