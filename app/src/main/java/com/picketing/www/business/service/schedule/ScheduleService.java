package com.picketing.www.business.service.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.business.domain.schedule.Schedule;
import com.picketing.www.business.domain.schedule.ScheduleFactory;
import com.picketing.www.persistence.repository.schedule.ScheduleRepository;
import com.picketing.www.persistence.table.schedule.DateScheduleView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleFactory scheduleFactory;

	private final ScheduleRepository scheduleRepository;

	public Schedule getSchedules(Long showId, LocalDate yearAndMonth) {
		List<DateScheduleView> dateScheduleViews = scheduleRepository.getSchedules(showId, yearAndMonth);
		return scheduleFactory.create(dateScheduleViews);
	}
}
