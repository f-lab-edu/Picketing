package com.picketing.www.presentation.controller.schedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picketing.www.business.domain.schedule.ScheduleFactory;
import com.picketing.www.business.service.schedule.ScheduleService;
import com.picketing.www.presentation.dto.request.schedule.ScheduleSearchRequest;
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
		@ModelAttribute ScheduleSearchRequest scheduleSearchRequest
	) {
		return scheduleFactory.findResponse(scheduleService.getSchedules(showId, scheduleSearchRequest.yearAndMonth()));
	}
}
