package com.picketing.www.presentation.controller.reservation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picketing.www.business.domain.reservation.ReservationFactory;
import com.picketing.www.business.service.reservation.ReservationService;
import com.picketing.www.business.service.show.ShowService;
import com.picketing.www.presentation.dto.request.reservation.ReservationRequest;
import com.picketing.www.presentation.dto.response.reservation.MakeReservationResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	private final ReservationFactory reservationFactory;
	private final ReservationService reservationService;

	private final ShowService showService;

	@PostMapping
	public MakeReservationResponse makeReservation(@RequestBody ReservationRequest requestDto) {
		return reservationFactory.convertReservationToResponse(
			reservationService.makeReservations(showService.getShowById(requestDto.showId()), requestDto)
		);
	}
}
