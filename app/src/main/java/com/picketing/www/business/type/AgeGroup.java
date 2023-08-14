package com.picketing.www.business.type;

import lombok.Getter;

@Getter
public enum AgeGroup {

	ALL(0, "전체 관람가"),
	SEVEN(7, "만 7세 이상 관람가"),
	NINE(9, "만 9세 이상 관람가"),
	FOURTEEN(14, "만 14세 이상 관람가"),
	NINETEEN(19, "만 19세 이상 관람가");

	private final int minimumAge;
	private final String details;

	AgeGroup(int minimumAge, String details) {
		this.minimumAge = minimumAge;
		this.details = details;
	}
}
