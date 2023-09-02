package com.picketing.www.business.service.show;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.business.domain.show.SeatGrade;
import com.picketing.www.business.domain.show.ShowFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowAggregateService {

	private final ShowFactory showFactory;

	private SeatGradeService seatGradeService;
	private ShowService showService;

	@Transactional(readOnly = true)
	public List<SeatGrade> getRemainingSeatsByShowAndSchedule(Long showId, Long scheduleId) {
		showService.checkExistShow(showId);
		// schedule 있는지 체크

		// schedule 과 함께 seat 조회
		return seatGradeService.gets(showId, scheduleId);
	}
}
