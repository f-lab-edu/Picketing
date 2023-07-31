package com.picketing.www.presentation.dto.response.user;

import lombok.Builder;

@Builder
public record UserDetailResponse(
	String email,
	String name
) {
}
