package com.picketing.www.business.service.show.seatgrade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.picketing.www.business.service.show.seatgrade.dto.RemainingSeatGradeCount;
import com.picketing.www.persistence.repository.show.seatgrade.SeatGradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeRepository seatGradeRepository;

	List<RemainingSeatGradeCount> getRemainingSeatGradeCount() {
		return null;
	}
}
