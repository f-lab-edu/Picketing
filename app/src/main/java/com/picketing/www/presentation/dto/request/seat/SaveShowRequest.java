package com.picketing.www.presentation.dto.request.seat;

import java.time.LocalDate;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import lombok.Builder;

@Builder
public record SaveShowRequest(

	String title,

	Genre genre,

	SubGenre subGenre,

	LocalDate startDate,

	LocalDate endDate,

	String venue,

	Long runningTime,

	Long intermission,

	AgeGroup ageGroup,

	String details,

	boolean isBookable,

	Long ownerId
) {
}
