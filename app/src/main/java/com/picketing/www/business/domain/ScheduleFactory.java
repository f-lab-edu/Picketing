package com.picketing.www.business.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.schedule.DateSchedulePersist;
import com.picketing.www.persistence.table.schedule.TimeSchedulePersist;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto.DateScheduleResponseDto;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto.DateScheduleResponseDto.TimeScheduleResponseDto;

@Component
public class ScheduleFactory {
	public DateSchedule create(DateSchedulePersist dateSchedulePersist) {
		return DateSchedule.builder()
			.id(dateSchedulePersist.id())
			.name(dateSchedulePersist.name())
			.showId(dateSchedulePersist.showId())
			.startDate(dateSchedulePersist.startDate())
			.endDate(dateSchedulePersist.endDate())
			.build();
	}

	public TimeSchedule create(TimeSchedulePersist timeSchedulePersist) {
		return TimeSchedule.builder()
			.id(timeSchedulePersist.id())
			.startTime(timeSchedulePersist.startTime())
			.endTime(timeSchedulePersist.endTime())
			.build();
	}

	public ScheduleResponseDto findResponse(List<DateSchedule> dateSchedules) {
		return new ScheduleResponseDto(this.findDateResponses(dateSchedules));
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
