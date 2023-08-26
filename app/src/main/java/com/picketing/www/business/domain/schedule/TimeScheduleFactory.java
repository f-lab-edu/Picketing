package com.picketing.www.business.domain.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.schedule.TimeSchedulePersist;

@Component
public class TimeScheduleFactory {
	public TimeSchedule create(TimeSchedulePersist timeSchedulePersist) {
		return TimeSchedule.builder()
			.id(timeSchedulePersist.id())
			.startTime(timeSchedulePersist.startTime())
			.endTime(timeSchedulePersist.endTime())
			.build();
	}

	public List<TimeSchedule> create(List<TimeSchedulePersist> timeSchedulePersists) {
		List<TimeSchedule> timeSchedules = new ArrayList<>();
		for (TimeSchedulePersist timeSchedulePersist : timeSchedulePersists) {
			timeSchedules.add(create(timeSchedulePersist));
		}
		return timeSchedules;
	}
}
