package com.picketing.www.persistence.repository.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.business.type.YearAndMonth;
import com.picketing.www.persistence.table.schedule.DateSchedulePersist;
import com.picketing.www.persistence.table.schedule.DateScheduleView;
import com.picketing.www.persistence.table.schedule.TimeSchedulePersist;

@Repository
public class ScheduleRepository {

	private final Map<Long, DateSchedulePersist> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);

	private final Map<YearAndMonth, List<DateSchedulePersist>> yearAndMonthIndex = new ConcurrentHashMap<>();
	private final Map<DateSchedulePersist, List<TimeSchedulePersist>> dateAndTimeScheduleIndex = new ConcurrentHashMap<>();

	public List<DateScheduleView> getSchedules(Long showId, final LocalDate searchTargetDate) {
		List<DateScheduleView> dateScheduleViews = yearAndMonthIndex.get(new YearAndMonth(searchTargetDate)).stream()
			.map(DateScheduleView::new)
			.filter(dateScheduleView -> dateScheduleView.dateSchedulePersist().showId().equals(showId))
			.toList();
		for (DateScheduleView dateScheduleView : dateScheduleViews) {
			List<TimeSchedulePersist> timeSchedulePersists = dateAndTimeScheduleIndex.get(
				dateScheduleView.dateSchedulePersist());
			dateScheduleView.timeSchedulePersists().addAll(timeSchedulePersists);
		}
		return dateScheduleViews;
	}
}
