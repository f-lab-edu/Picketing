package com.picketing.www.presentation.dto.response.show;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ShowSeatPriceResponse(
	Long showId,
	String seatGradeName,
	BigDecimal price
) {
}
