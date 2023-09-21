package com.picketing.www.business.domain.schedule;

import java.util.List;

import lombok.Builder;

@Builder
public class Schedule {

	List<DateSchedule> dateSchedules;
	//
	// @Builder
	// public Schedule(List<DateSchedule> dateSchedules) {
	// 	this.dateSchedules = dateSchedules;
	// }
}
