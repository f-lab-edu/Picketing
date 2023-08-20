package com.picketing.www.business.service.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.business.domain.DateSchedule;
import com.picketing.www.business.domain.ScheduleFactory;
import com.picketing.www.business.domain.TimeSchedule;
import com.picketing.www.persistence.repository.schedule.DateScheduleRepository;
import com.picketing.www.persistence.repository.schedule.TimeScheduleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleFactory scheduleFactory;

	private final DateScheduleRepository dateScheduleRepository;
	private final TimeScheduleRepository timeScheduleRepository;

	public List<DateSchedule> getSchedules(Long showId, LocalDate yearAndMonth) {
		List<DateSchedule> dateSchedules = dateScheduleRepository.getSchedules(showId, yearAndMonth)
			.stream()
			.map(scheduleFactory::create)
			.toList();
		for (DateSchedule dateSchedule : dateSchedules) {
			Long dateScheduleId = dateSchedule.getId();
			List<TimeSchedule> timeSchedules = timeScheduleRepository.getTimeSchedules(dateScheduleId)
				.stream()
				.map(scheduleFactory::create)
				.toList();
			dateSchedule.setTimeSchedules(timeSchedules);
		}
		return dateSchedules;
	}
}
