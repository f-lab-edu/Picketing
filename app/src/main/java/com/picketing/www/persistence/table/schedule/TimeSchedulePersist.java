package com.picketing.www.persistence.table.schedule;

import java.time.LocalTime;

public record TimeSchedulePersist(
	Long id,
	Long dateScheduleId,
	LocalTime startTime,
	LocalTime endTime
) {
	public TimeSchedulePersist(Long id, TimeSchedulePersist timeSchedulePersist) {
		this(id, timeSchedulePersist.dateScheduleId, timeSchedulePersist.startTime, timeSchedulePersist.endTime);
	}
}
