package com.picketing.www.presentation.dto.request.seat;

import com.picketing.www.business.domain.show.Show;

import lombok.Builder;

@Builder
public record SaveSeatRequest(
	Show show,

	Long timeScheduleId

) {
}
