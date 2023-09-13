package com.picketing.www.business.domain.show;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.response.show.ShowMainResponse;

@Component
public class ShowFactory {

	public ShowMainResponse findResponse(Show show) {
		return ShowMainResponse.builder()
			.id(show.getId())
			.title(show.getTitle())
			.genre(show.getGenre().getGenre())
			.subGenre(show.getSubGenre().getSubGenre())
			.startDate(show.getStartDate())
			.endDate(show.getEndDate())
			.venue(show.getVenue())
			.ageGroup(show.getAgeGroup().getDetails())
			.isBookable(show.isBookable() ? "Y" : "N")
			.build();
	}
}
