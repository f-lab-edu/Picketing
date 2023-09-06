package com.picketing.www.persistence.repository.show;

import java.util.List;

import com.picketing.www.persistence.table.SeatGradePersist;

public interface SeatGradeRepository {
	List<SeatGradePersist> findSeatGradeByShowIdAndTime(Long showId, Long scheduleId);
}
