package com.picketing.www.business.domain.show;

import java.time.LocalDate;
import java.util.List;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import lombok.Builder;
import lombok.Getter;

@Builder
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
	final Long ownerId;

	@Getter
	final List<ShowSeatGrade> seatGrades;
	boolean isBookable;

	public void changeBookable(boolean flag) {
		this.isBookable = flag;
	}
}
