package com.picketing.www.persistence.table;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import lombok.Builder;

@Builder
public record ShowPersist(
	Long id,
	String title,
	Genre genre,
	SubGenre subGenre,
	LocalDate startDate,
	LocalDate endDate,
	String venue,
	Long runningTime,
	Long intermission,
	String details,
	boolean isBookable,
	LocalDateTime createdAt,
	LocalDateTime modifiedAt

) {
}
