package com.picketing.www.persistence.repository.show;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picketing.www.persistence.table.ShowSeatGradePersist;

public interface SeatGradeRepository extends JpaRepository<ShowSeatGradePersist, Long> {
	List<ShowSeatGradePersist> findAllByShowId(Long showId, Long scheduleId);
}
