package com.picketing.www.business.service.reservation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.presentation.dto.request.reservation.ReservationRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;

	@Transactional
	public List<Reservation> makeReserations(Long showId, List<ReservationRequest> reservationRequests) {
		return reservationRepository.save();
	}
}
