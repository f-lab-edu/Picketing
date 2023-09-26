package com.picketing.www.business.domain.schedule;

import java.util.List;

import lombok.Builder;

public class Schedule {
	long showId;
	long theaterId;
	List<DateSchedule> dateSchedules;

	@Builder
	public Schedule(List<DateSchedule> dateSchedules) {
		this.dateSchedules = dateSchedules;
	}
}
