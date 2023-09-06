package com.picketing.www.business.service.show;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.business.domain.show.SeatGrade;
import com.picketing.www.business.domain.show.SeatGradeFactory;
import com.picketing.www.persistence.repository.show.SeatGradeRepository;
import com.picketing.www.persistence.table.SeatGradePersist;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeFactory seatGradeFactory;

	private final SeatGradeRepository seatGradeRepository;

	@Transactional(readOnly = true)
	public List<SeatGrade> gets(Long showId, Long scheduleId) {
		List<SeatGradePersist> seatGradePersists = seatGradeRepository.findSeatGradeByShowIdAndTime(showId, scheduleId);
		return seatGradePersists.stream()
			.map(seatGradeFactory::create)
			.toList();
	}
}
