package com.picketing.www.presentation.dto.response.show;

import java.time.LocalDateTime;

public record ShowDetailResponse(
	Long id,
	String title,
	String genre,
	LocalDateTime startDate,
	LocalDateTime endDate,
	String venue,
	String ageGroup,
	String isBookable,
	Long runningTime,
	Long intermission,
	String details
) {
}
