package com.picketing.www.persistence.repository.schedule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.schedule.TimeSchedulePersist;

@Repository
public class TimeScheduleRepository {

	private final Map<Long, TimeSchedulePersist> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);

	private final Map<Long, List<TimeSchedulePersist>> dateIndex = new ConcurrentHashMap<>();

	public List<TimeSchedulePersist> getTimeSchedules(Long dateScheduleId) {
		return dateIndex.get(dateScheduleId);
	}
}
