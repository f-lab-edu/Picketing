package com.picketing.www.business.service.show.seat.grade;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.business.domain.show.seat.grade.SeatGrade;
import com.picketing.www.persistence.repository.show.seat.grade.SeatGradeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeRepository seatGradeRepository;

	@Transactional(readOnly = true)
	public List<SeatGrade> getRemainingSeatGradeCount(Long showId, Long timeScheduleId) {
		return seatGradeRepository.findAllByShowIdAndTimeScheduleId(showId,
			timeScheduleId);
	}
}
