package com.picketing.www.persistence.repository.schedule;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.schedule.DateSchedule;
import com.picketing.www.business.domain.schedule.TimeSchedule;

@Repository
public class ScheduleRepository {

	private DateScheduleRepository dateScheduleRepository;
	private TimeScheduleRepository timeScheduleRepository;

	// TODO 특정 공연에 대해서 DateSchedule 및 TimeSchedule까지 조회

	// TODO 특정 공연에 대해 DateSchedule 및 TimeSchedule 저장

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
