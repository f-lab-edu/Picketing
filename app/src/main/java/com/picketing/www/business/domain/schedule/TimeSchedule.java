package com.picketing.www.business.domain.schedule;

import java.time.LocalTime;

import lombok.Builder;

public class TimeSchedule {
	final Long id;
	final Long dateScheduleId;
	final String name;
	final LocalTime startTime;
	final LocalTime endTime;

	@Builder
	public TimeSchedule(Long id, Long dateScheduleId, String name, LocalTime startTime, LocalTime endTime) {
		this.id = id;
		this.dateScheduleId = dateScheduleId;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
