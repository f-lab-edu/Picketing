package com.picketing.www.persistence.repository.schedule;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.schedule.YearAndMonthForShow;
import com.picketing.www.persistence.table.schedule.DateSchedulePersist;
import com.picketing.www.persistence.table.schedule.DateScheduleView;
import com.picketing.www.persistence.table.schedule.TimeSchedulePersist;

@Repository
public class ScheduleRepository {

	private final Map<Long, DateSchedulePersist> dateStore = new ConcurrentHashMap<>();
	private final Map<Long, TimeSchedulePersist> timeStore = new ConcurrentHashMap<>();
	private final AtomicLong dateSequence = new AtomicLong(0);
	private final AtomicLong timeSequence = new AtomicLong(0);

	private final Map<YearAndMonthForShow, List<DateSchedulePersist>> yearAndMonthIndex = new ConcurrentHashMap<>();
	private final Map<DateSchedulePersist, List<TimeSchedulePersist>> dateAndTimeScheduleIndex = new ConcurrentHashMap<>();

	public List<DateScheduleView> getSchedules(Long showId, final YearMonth searchTargetYearMonth) {
		YearAndMonthForShow yearAndMonthForShow = new YearAndMonthForShow(
			searchTargetYearMonth, showId);
		List<DateScheduleView> dateScheduleViews = yearAndMonthIndex.get(yearAndMonthForShow)
			.stream()
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

	public List<DateScheduleView> save(List<DateScheduleView> dateScheduleViews) {
		List<DateScheduleView> persistedSource = new ArrayList<>();
		for (DateScheduleView dateScheduleView : dateScheduleViews) {
			DateSchedulePersist persistedDate = save(dateScheduleView.dateSchedulePersist());
			List<TimeSchedulePersist> persistedTime = save(dateScheduleView.dateSchedulePersist(),
				dateScheduleView.timeSchedulePersists());
			persistedSource.add(new DateScheduleView(persistedDate, persistedTime));
		}
		return persistedSource;
	}

	private DateSchedulePersist save(DateSchedulePersist dateSchedulePersist) {
		long id = dateSequence.incrementAndGet();
		DateSchedulePersist persistTarget = new DateSchedulePersist(id, dateSchedulePersist);
		dateStore.put(id, persistTarget);
		indexing(persistTarget);
		return persistTarget;
	}

	private void indexing(DateSchedulePersist dateSchedulePersist) {
		LocalDate startDate = dateSchedulePersist.startDate();
		long periodDay = ChronoUnit.DAYS.between(dateSchedulePersist.startDate(), dateSchedulePersist.endDate());
		do {
			YearMonth yearMonth = YearMonth.of(startDate.getYear(), startDate.getMonthValue());
			YearAndMonthForShow yearAndMonthForShow = new YearAndMonthForShow(yearMonth, dateSchedulePersist.showId());
			List<DateSchedulePersist> index = yearAndMonthIndex.getOrDefault(yearAndMonthForShow, new ArrayList<>());
			index.add(dateSchedulePersist);
			yearAndMonthIndex.put(yearAndMonthForShow, index);
			startDate = startDate.plusDays(1);
		} while (0 < periodDay--);
	}

	private List<TimeSchedulePersist> save(DateSchedulePersist dateSchedulePersist,
		List<TimeSchedulePersist> timeSchedulePersists) {
		List<TimeSchedulePersist> persistedSource = new ArrayList<>();
		for (TimeSchedulePersist timeSchedulePersist : timeSchedulePersists) {
			long id = timeSequence.incrementAndGet();
			TimeSchedulePersist persistTarget = new TimeSchedulePersist(id, timeSchedulePersist);
			timeStore.put(id, persistTarget);
			persistedSource.add(persistTarget);
		}
		indexing(dateSchedulePersist, persistedSource);
		return persistedSource;
	}

	private void indexing(DateSchedulePersist dateSchedulePersist,
		List<TimeSchedulePersist> timeSchedulePersists) {
		List<TimeSchedulePersist> index = dateAndTimeScheduleIndex.getOrDefault(dateSchedulePersist, new ArrayList<>());
		index.addAll(timeSchedulePersists);
		dateAndTimeScheduleIndex.put(dateSchedulePersist, index);
	}
}
