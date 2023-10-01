package com.picketing.www.business.service.entertainment.seat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.entertainment.seat.SeatGrade;
import com.picketing.www.persistence.repository.entertainment.seat.SeatGradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeRepository seatGradeRepository;

	public List<SeatGrade> getSeatGradeList(Long showId) {
		return seatGradeRepository.findAllByShow(showId);
	}
}
