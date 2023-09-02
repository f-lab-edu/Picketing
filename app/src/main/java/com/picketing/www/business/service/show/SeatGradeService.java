package com.picketing.www.business.service.show;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.business.domain.show.SeatGrade;
import com.picketing.www.business.domain.show.SeatGradeFactory;
import com.picketing.www.persistence.repository.show.ShowRepository;
import com.picketing.www.persistence.table.SeatGradePersist;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class SeatGradeService {

	private final SeatGradeFactory seatGradeFactory;

	private final ShowRepository showRepository;

	@Transactional(readOnly = true)
	public List<SeatGrade> gets(Long showId, Long scheduleId) {
		// jpa 야 빨리 와라...
		List<SeatGradePersist> seatGradePersists = showRepository.findSeatGradeByShowIdAndTime(showId, scheduleId);
		return seatGradePersists.stream()
			.map(seatGradeFactory::create)
			.toList();
	}
}
