package com.picketing.www.persistence.table.schedule;

import java.time.LocalTime;

public record TimeSchedulePersist(
	Long id,
	Long dateScheduleId,
	LocalTime startTime,
	LocalTime endTime
) {
}
