package com.picketing.www.presentation.dto.request.seat;

import com.picketing.www.business.domain.entertainment.Entertainment;

import lombok.Builder;

@Builder
public record SaveSeatRequest(
	Entertainment entertainment,

	Long timeScheduleId

) {
}
