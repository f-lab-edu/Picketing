package com.picketing.www.persistence.table.schedule;

import java.util.ArrayList;
import java.util.List;

public record DateScheduleView(
	DateSchedulePersist dateSchedulePersist,
	List<TimeSchedulePersist> timeSchedulePersists
) {
	public DateScheduleView(DateSchedulePersist dateSchedulePersist, List<TimeSchedulePersist> timeSchedulePersists) {
		this.dateSchedulePersist = dateSchedulePersist;
		this.timeSchedulePersists = timeSchedulePersists;
	}

	public DateScheduleView(DateSchedulePersist dateSchedulePersist) {
		this(dateSchedulePersist, new ArrayList<>());
	}
}
