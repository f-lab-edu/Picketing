package com.picketing.www.business.domain.show;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.ShowPersist;
import com.picketing.www.presentation.dto.response.show.ShowMainResponse;

@Component
public class ShowFactory {

	public ShowPersist create(Show show) {
		return ShowPersist.builder()
			.id(show.id)
			.title(show.title)
			.genre(show.genre)
			.subGenre(show.subGenre)
			.startDate(show.startDate)
			.endDate(show.endDate)
			.venue(show.venue)
			.runningTime(show.runningTime)
			.intermission(show.intermission)
			.ageGroup(show.ageGroup)
			.details(show.details)
			.isBookable(show.isBookable)
			.ownerId(show.ownerId)
			.createdAt(LocalDateTime.now())
			.modifiedAt(null)
			.build();
	}

	public Show convertToEntity(ShowPersist showPersist) {
		return Show.builder()
			.id(showPersist.getId())
			.title(showPersist.getTitle())
			.genre(showPersist.getGenre())
			.subGenre(showPersist.getSubGenre())
			.startDate(showPersist.getStartDate())
			.endDate(showPersist.getEndDate())
			.venue(showPersist.getVenue())
			.runningTime(showPersist.getRunningTime())
			.intermission(showPersist.getIntermission())
			.ageGroup(showPersist.getAgeGroup())
			.details(showPersist.getDetails())
			.isBookable(showPersist.isBookable())
			.ownerId(showPersist.getOwnerId())
			.build();
	}

	public ShowMainResponse findResponse(Show show) {
		return ShowMainResponse.builder()
			.id(show.id)
			.title(show.title)
			.genre(show.genre.getGenre())
			.subGenre(show.subGenre.getSubGenre())
			.startDate(show.startDate)
			.endDate(show.endDate)
			.venue(show.venue)
			.ageGroup(show.ageGroup.getDetails())
			.isBookable(show.isBookable ? "Y" : "N")
			.build();
	}
}
