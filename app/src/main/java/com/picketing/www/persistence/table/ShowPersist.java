package com.picketing.www.persistence.table;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShowPersist {
	Long id;
	String title;
	Genre genre;
	SubGenre subGenre;
	LocalDate startDate;
	LocalDate endDate;
	String venue;
	Long runningTime;
	Long intermission;

	AgeGroup ageGroup;
	String details;
	boolean isBookable;
	Long ownerId;
	LocalDateTime createdAt;
	LocalDateTime modifiedAt;

	public ShowPersist createShowPersist(Long id) {
		this.id = id;
		return this;
	}
}
