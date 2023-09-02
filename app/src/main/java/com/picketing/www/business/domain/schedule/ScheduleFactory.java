package com.picketing.www.business.domain.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.schedule.DateSchedulePersist;
import com.picketing.www.persistence.table.schedule.DateScheduleView;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto.DateScheduleResponseDto;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto.DateScheduleResponseDto.TimeScheduleResponseDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduleFactory {

	private final DateScheduleFactory dateScheduleFactory;
	private final TimeScheduleFactory timeScheduleFactory;

	public Schedule create(List<DateScheduleView> dateScheduleViews) {
		List<DateSchedule> dateSchedules = new ArrayList<>(dateScheduleViews.size());
		for (DateScheduleView dateScheduleView : dateScheduleViews) {
			DateSchedulePersist dateSchedulePersist = dateScheduleView.dateSchedulePersist();
			DateSchedule dateSchedule = dateScheduleFactory.create(dateSchedulePersist);
			List<TimeSchedule> timeSchedules = timeScheduleFactory.create(dateScheduleView.timeSchedulePersists());
			dateSchedule.setTimeSchedules(timeSchedules);
			dateSchedules.add(dateSchedule);
		}
		return Schedule.builder()
			.dateSchedules(dateSchedules)
			.build();
	}

	public ScheduleResponseDto findResponse(Schedule schedule) {
		return new ScheduleResponseDto(this.findDateResponses(schedule.dateSchedules));
	}

	private List<DateScheduleResponseDto> findDateResponses(List<DateSchedule> dateSchedules) {
		return dateSchedules.stream().map(this::findDateResponse).toList();
	}

	private DateScheduleResponseDto findDateResponse(DateSchedule dateSchedule) {
		return new ScheduleResponseDto.DateScheduleResponseDto(
			dateSchedule.name,
			dateSchedule.startDate,
			dateSchedule.endDate,
			this.findTimeResponses(dateSchedule.timeSchedules)
		);
	}

	private List<TimeScheduleResponseDto> findTimeResponses(List<TimeSchedule> timeSchedules) {
		return timeSchedules.stream()
			.map(this::findTimeResponse)
			.toList();
	}

	private TimeScheduleResponseDto findTimeResponse(TimeSchedule timeSchedule) {
		return new TimeScheduleResponseDto(
			timeSchedule.startTime,
			timeSchedule.endTime
		);
	}
}
