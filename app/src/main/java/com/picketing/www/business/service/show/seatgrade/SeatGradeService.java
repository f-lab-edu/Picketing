package com.picketing.www.business.service.show.seatgrade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seatgrade.SeatGrade;
import com.picketing.www.persistence.repository.show.seatgrade.SeatGradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeRepository seatGradeRepository;

	public List<SeatGrade> getSeatGradePriceList(Show show) {
		return seatGradeRepository.findAllByShow(show);
	}
}
