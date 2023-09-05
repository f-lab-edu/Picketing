package com.picketing.www.presentation.dto.response.schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ScheduleResponseDto(
	List<DateScheduleResponseDto> schedules
) {
	public record DateScheduleResponseDto(
		String name,
		LocalDate startDate,
		LocalDate endDate,
		List<TimeScheduleResponseDto> timeSchedules
	) {
		public record TimeScheduleResponseDto(
			LocalTime startTime,
			LocalTime endTime
		) {
		}
	}
}
