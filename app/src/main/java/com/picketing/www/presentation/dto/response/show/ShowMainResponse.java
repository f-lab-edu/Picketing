package com.picketing.www.presentation.dto.response.show;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ShowMainResponse(
	Long id,
	String title,
	String genre,
	String subGenre,
	LocalDate startDate,
	LocalDate endDate,
	String venue,
	String ageGroup,
	String isBookable
) {
}
