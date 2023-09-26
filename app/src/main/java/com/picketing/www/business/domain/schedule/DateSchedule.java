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
	final LocalDate showDate; //2023-09-26

	@Setter
	List<TimeSchedule> timeSchedules;
}
