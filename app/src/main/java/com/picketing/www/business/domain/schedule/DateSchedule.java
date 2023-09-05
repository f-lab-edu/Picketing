package com.picketing.www.business.domain.schedule;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class DateSchedule {
	@Getter
	final Long id;
	final Long showId;
	final String name;
	final LocalDate startDate;
	final LocalDate endDate;

	@Setter
	List<TimeSchedule> timeSchedules;
}
