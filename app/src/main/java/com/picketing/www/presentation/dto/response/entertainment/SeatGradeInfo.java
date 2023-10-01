package com.picketing.www.presentation.dto.response.entertainment;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class SeatGradeInfo {

	private final Long showId;

	private final String seatGradeName;

	private final BigDecimal price;

	public SeatGradeInfo(Long showId, String seatGradeName, BigDecimal price) {
		this.showId = showId;
		this.seatGradeName = seatGradeName;
		this.price = price;
	}
}
