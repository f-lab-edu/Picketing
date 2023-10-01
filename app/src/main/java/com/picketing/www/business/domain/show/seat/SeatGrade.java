package com.picketing.www.business.domain.show.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatGrade {

	VIP("VIP", 165000L),
	S("S", 154000L),
	R("R", 143000L),
	A("A", 121000L);

	private final String name;

	private final Long price;
}
