package com.picketing.www.business.service.show.seat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.persistence.repository.show.seat.SeatGradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeRepository seatGradeRepository;

	public List<SeatGrade> getSeatGradeList(Long showId) {
		System.out.println("SeatGradeService.getSeatGradeList , showId : " + showId);
		List<SeatGrade> allByShow = seatGradeRepository.findAllByShow(showId);
		for (SeatGrade seatGrade : allByShow) {
			System.out.println("seatGrade.toString() = " + seatGrade.toString());
		}

		return allByShow;
	}
}
