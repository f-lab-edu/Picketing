package com.picketing.www.presentation.dto.request.seat;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record SaveSeatGradeRequest(
	String name,
	Seat seat,
	BigDecimal price
) {
}
