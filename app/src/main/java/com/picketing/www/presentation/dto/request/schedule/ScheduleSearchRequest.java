package com.picketing.www.presentation.dto.request.schedule;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record ScheduleSearchRequest(
	@DateTimeFormat(style = "yyyy.MM")
	LocalDate yearAndMonth
) {
}
