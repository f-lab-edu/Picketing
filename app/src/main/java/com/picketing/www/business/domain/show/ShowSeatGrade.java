package com.picketing.www.business.domain.show;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PACKAGE)
public class ShowSeatGrade {
	private Long id;
	private String name;
	private Long remainingSeatCount;
	private Show show; // mapped show id
}
