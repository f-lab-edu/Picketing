package com.picketing.www.business.domain.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.schedule.DateScheduleView;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto.DateScheduleResponseDto;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto.DateScheduleResponseDto.TimeScheduleResponseDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduleFactory {
	public Schedule create(List<DateScheduleView> dateScheduleViews) {
		List<DateSchedule> dateSchedules = new ArrayList<>(dateScheduleViews.size());
		for (DateScheduleView dateScheduleView : dateScheduleViews) {
			DateSchedule dateSchedule = dateScheduleView.dateSchedule();
			List<TimeSchedule> timeSchedules = dateScheduleView.timeSchedules();
			// dateSchedule.setTimeSchedules(timeSchedules);
			// dateSchedules.add(dateSchedule);
		}
		return Schedule.builder()
			.dateSchedules(dateSchedules)
			.build();
	}

	public ScheduleResponseDto convertScheduleIntoResponse(Schedule schedule) {
		return new ScheduleResponseDto(this.convertDateSchedulesIntoResponse(schedule.dateSchedules));
	}

	private List<DateScheduleResponseDto> convertDateSchedulesIntoResponse(List<DateSchedule> dateSchedules) {
		return dateSchedules.stream().map(this::convertDateScheduleIntoResponse).toList();
	}

	private DateScheduleResponseDto convertDateScheduleIntoResponse(DateSchedule dateSchedule) {
		return new ScheduleResponseDto.DateScheduleResponseDto(
			dateSchedule.getName(),
			dateSchedule.getStartDate(),
			dateSchedule.getEndDate(),
			this.convertTimeScheduleListIntoResponse(dateSchedule.getTimeSchedules())
		);
	}

	private List<TimeScheduleResponseDto> convertTimeScheduleListIntoResponse(List<TimeSchedule> timeSchedules) {
		return timeSchedules.stream()
			.map(this::convertTimeScheduleListIntoResponse)
			.toList();
	}

	private TimeScheduleResponseDto convertTimeScheduleListIntoResponse(TimeSchedule timeSchedule) {
		return new TimeScheduleResponseDto(
			timeSchedule.getStartTime(),
			timeSchedule.getEndTime()
		);
	}
}
