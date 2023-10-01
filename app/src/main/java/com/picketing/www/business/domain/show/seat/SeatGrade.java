package com.picketing.www.business.domain.show.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatGradeEnum {

	VIP("VIP", 165000L),
	S("S", 165000),
	R("R", 165000),
	A("A", 165000);

	private final String name;

	private final Long price;
}
