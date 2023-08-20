package com.picketing.www.persistence.repository.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.business.type.YearAndMonth;
import com.picketing.www.persistence.table.schedule.DateSchedulePersist;

@Repository
public class DateScheduleRepository {

	private final Map<Long, DateSchedulePersist> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);

	private final Map<YearAndMonth, List<DateSchedulePersist>> yearAndMonthIndex = new ConcurrentHashMap<>();

	public List<DateSchedulePersist> getSchedules(Long showId, final LocalDate searchTargetDate) {
		List<DateSchedulePersist> dateSchedulePersists = yearAndMonthIndex.get(new YearAndMonth(searchTargetDate));
		List<DateSchedulePersist> cloneSchedule = new ArrayList<>();
		for (DateSchedulePersist dateSchedulePersist : dateSchedulePersists) {
			if (dateSchedulePersist.showId().equals(showId)) {
				cloneSchedule.add(dateSchedulePersist);
			}
		}
		return cloneSchedule;
	}
}
