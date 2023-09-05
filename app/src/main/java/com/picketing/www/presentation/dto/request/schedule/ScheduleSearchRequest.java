package com.picketing.www.presentation.dto.request.schedule;

import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

public record ScheduleSearchRequest(
	@RequestParam
	@DateTimeFormat(style = "yyyy-MM")
	YearMonth yearAndMonth
) {
}
