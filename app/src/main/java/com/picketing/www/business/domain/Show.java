package com.picketing.www.business.domain;

import java.time.LocalDate;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import lombok.Builder;

public class Show {

	final Long id;
	final String title;
	final Genre genre;
	final SubGenre subGenre;
	final LocalDate startDate;
	final LocalDate endDate;
	final String venue;
	final Long runningTime;
	final Long intermission;

	final AgeGroup ageGroup;
	final String details;

	boolean isBookable;

	@Builder
	Show(Long id, String title, Genre genre, SubGenre subGenre, LocalDate startDate, LocalDate endDate, String venue,
		Long runningTime, Long intermission, AgeGroup ageGroup, String details, boolean isBookable) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.subGenre = subGenre;
		this.startDate = startDate;
		this.endDate = endDate;
		this.venue = venue;
		this.runningTime = runningTime;
		this.intermission = intermission;
		this.ageGroup = ageGroup;
		this.details = details;
		this.isBookable = isBookable;
	}

	public void changeBookable(boolean flag) {
		this.isBookable = flag;
	}
}
