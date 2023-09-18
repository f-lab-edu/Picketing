package com.picketing.www.presentation.dto.response.show;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record ShowSeatPriceResponse(

	@JsonProperty("basic_price_list")
	List<ShowSeatBasicPriceResponseDto> basicPriceList
) {

	@Builder
	public record ShowSeatBasicPriceResponseDto(
		@JsonProperty("show_id")
		Long showId,

		@JsonProperty("seat_grade_name")
		String seatGradeName,
		BigDecimal price
	) {
	}
}
