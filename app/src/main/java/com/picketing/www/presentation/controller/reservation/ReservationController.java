package com.picketing.www.presentation.controller.reservation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@PostMapping("/{showId}")
	public String makeReservation(@PathVariable Long showId) {
		return null;
	}
}
