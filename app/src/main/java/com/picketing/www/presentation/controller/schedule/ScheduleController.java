package com.picketing.www.presentation.controller.schedule;

import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picketing.www.business.domain.schedule.ScheduleFactory;
import com.picketing.www.business.service.schedule.ScheduleService;
import com.picketing.www.presentation.dto.response.schedule.ScheduleResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;
	private final ScheduleFactory scheduleFactory;

	@GetMapping("/{showId}/schedules")
	public ScheduleResponseDto get(
		@PathVariable Long showId,
		@RequestParam("yearMonth") @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth
	) {
		return scheduleFactory.findResponse(
			scheduleService.getSchedules(showId, yearMonth)
		);
	}
}
