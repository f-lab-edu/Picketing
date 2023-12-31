package com.picketing.www.business.domain.schedule;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.schedule.DateSchedulePersist;

@Component
public class DateScheduleFactory {
	public DateSchedule create(DateSchedulePersist dateSchedulePersist) {
		return DateSchedule.builder()
			.id(dateSchedulePersist.id())
			.name(dateSchedulePersist.name())
			.showId(dateSchedulePersist.showId())
			.startDate(dateSchedulePersist.startDate())
			.endDate(dateSchedulePersist.endDate())
			.build();
	}
}
