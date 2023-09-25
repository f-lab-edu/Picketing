package com.picketing.www.persistence.table.schedule;

import java.util.ArrayList;
import java.util.List;

import com.picketing.www.business.domain.schedule.DateSchedule;
import com.picketing.www.business.domain.schedule.TimeSchedule;

import lombok.Builder;

@Builder
public record DateScheduleView(
	DateSchedule dateSchedule,
	List<TimeSchedule> timeSchedules
) {
	public DateScheduleView(DateSchedule dateSchedule, List<TimeSchedule> timeSchedules) {
		this.dateSchedule = dateSchedule;
		this.timeSchedules = timeSchedules;
	}

	public DateScheduleView(DateSchedule dateSchedule) {
		this(dateSchedule, new ArrayList<>());
	}
}
