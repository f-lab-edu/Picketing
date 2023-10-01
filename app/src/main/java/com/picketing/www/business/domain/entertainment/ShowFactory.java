package com.picketing.www.business.domain.entertainment;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.request.seat.SaveShowRequest;
import com.picketing.www.presentation.dto.response.entertainment.EntertainmentMainResponse;

@Component
public class ShowFactory {

	public EntertainmentMainResponse findResponse(Entertainment entertainment) {
		return EntertainmentMainResponse.builder()
			.id(entertainment.getId())
			.title(entertainment.getTitle())
			.genre(entertainment.getGenre().getGenre())
			.subGenre(entertainment.getSubGenre().getSubGenre())
			.startDate(entertainment.getStartDate())
			.endDate(entertainment.getEndDate())
			.venue(entertainment.getVenue())
			.ageGroup(entertainment.getAgeGroup().getDetails())
			.isBookable(entertainment.isBookable() ? "Y" : "N")
			.build();
	}

	public Entertainment createShow(SaveShowRequest saveShowRequest) {
		return Entertainment.builder()
			.title(saveShowRequest.title())
			.genre(saveShowRequest.genre())
			.subGenre(saveShowRequest.subGenre())
			.startDate(saveShowRequest.startDate())
			.endDate(saveShowRequest.endDate())
			.venue(saveShowRequest.venue())
			.runningTime(saveShowRequest.runningTime())
			.intermission(saveShowRequest.intermission())
			.ageGroup(saveShowRequest.ageGroup())
			.details(saveShowRequest.details())
			.isBookable(saveShowRequest.isBookable())
			.ownerId(saveShowRequest.ownerId())
			.build();
	}
}
