package com.picketing.www.presentation.dto.response.entertainment;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record EntertainmentMainResponse(
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
