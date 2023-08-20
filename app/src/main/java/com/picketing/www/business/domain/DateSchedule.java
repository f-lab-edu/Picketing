package com.picketing.www.business.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class DateSchedule {
	@Getter
	final Long id;
	final Long showId;
	final String name;
	final LocalDate startDate;
	final LocalDate endDate;

	@Setter
	List<TimeSchedule> timeSchedules;

	@Builder
	public DateSchedule(Long id, Long showId, String name, LocalDate startDate, LocalDate endDate,
		List<TimeSchedule> timeSchedules) {
		this.id = id;
		this.showId = showId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.timeSchedules = timeSchedules;
	}
}
