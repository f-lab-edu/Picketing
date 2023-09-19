package com.picketing.www.presentation.dto.response.show;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record ShowSeatPriceResponse(
	List<ShowSeatBasicPriceResponseDto> basicPriceList
) {

	public ShowSeatPriceResponse(List<ShowSeatBasicPriceResponseDto> basicPriceList) {
		this.basicPriceList = basicPriceList;
	}

	@Builder
	public record ShowSeatBasicPriceResponseDto(
		Long showId,
		String seatGradeName,
		BigDecimal price
	) {
	}
}
