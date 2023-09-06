package com.picketing.www.business.domain.show;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PACKAGE)
public class SeatGrade {
	private Long id;
	private Show show; // mapped show id
	private String name;
	private BigDecimal price;
	private Long remainingSeatCount;
}
