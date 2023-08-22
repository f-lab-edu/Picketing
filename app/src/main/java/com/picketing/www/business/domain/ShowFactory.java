package com.picketing.www.business.domain;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.ShowPersist;
import com.picketing.www.presentation.dto.response.show.ShowMainResponse;

@Component
public class ShowFactory {

	public Show convertToEntity(ShowPersist showPersist) {
		return Show.builder()
			.id(showPersist.id())
			.title(showPersist.title())
			.genre(showPersist.genre())
			.subGenre(showPersist.subGenre())
			.startDate(showPersist.startDate())
			.endDate(showPersist.endDate())
			.venue(showPersist.venue())
			.runningTime(showPersist.runningTime())
			.intermission(showPersist.intermission())
			.ageGroup(showPersist.ageGroup())
			.details(showPersist.details())
			.isBookable(showPersist.isBookable())
			.ownerId(showPersist.ownerId())
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
			.isBookable("Y")
			.build();
	}
}
