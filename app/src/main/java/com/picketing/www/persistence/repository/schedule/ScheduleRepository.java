package com.picketing.www.persistence.repository.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.schedule.DateSchedule;
import com.picketing.www.business.domain.schedule.TimeSchedule;
import com.picketing.www.persistence.table.schedule.DateScheduleView;

@Repository
public class ScheduleRepository {

	private DateScheduleRepository dateScheduleRepository;
	private TimeScheduleRepository timeScheduleRepository;

	// TODO 특정 공연에 대해서 DateSchedule 및 TimeSchedule까지 조회
	// public List<DateScheduleView> getSchedules(Long showId, final YearMonth searchTargetYearMonth) {
	//
	// }

	// 특정 공연에 대해 DateSchedule 및 TimeSchedule 저장
	public List<DateScheduleView> save(List<DateScheduleView> dateScheduleViews) {
		List<DateScheduleView> dateScheduleViewList = new ArrayList<>();

		for (DateScheduleView dateScheduleView : dateScheduleViews) {
			DateSchedule savedDateSchedule = save(dateScheduleView.dateSchedule());
			List<TimeSchedule> savedTimeSchedules = save(savedDateSchedule, dateScheduleView.timeSchedules());
			dateScheduleViewList.add(
				DateScheduleView.builder()
					.dateSchedule(savedDateSchedule)
					.timeSchedules(savedTimeSchedules)
					.build());
		}
		return dateScheduleViewList;
	}

	// 특정 공연에 대해 DateSchedule 저장
	public DateSchedule save(DateSchedule dateSchedule) {
		return dateScheduleRepository.save(dateSchedule);
	}

	// 특정 공연의 날짜에 대해 TimeSchedule 목록 저장
	public List<TimeSchedule> save(DateSchedule dateSchedule, List<TimeSchedule> timeSchedules) {
		List<TimeSchedule> savingTimeScheduleList = timeSchedules.stream()
			.map(t -> t.addShowDateSchedule(dateSchedule))
			.toList();

		timeScheduleRepository.saveAll(savingTimeScheduleList);

		return savingTimeScheduleList;
	}
}
