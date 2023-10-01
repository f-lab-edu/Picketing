package com.picketing.www.business.domain.schedule;

import java.util.List;

import lombok.Builder;

@Builder
public class Schedule {

	private List<DateSchedule> dateSchedules;
}
