package com.picketing.www.persistence.repository.show.seat.grade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picketing.www.business.domain.show.seat.grade.SeatGrade;

public interface SeatGradeRepository extends JpaRepository<SeatGrade, Long> {
	List<SeatGrade> findAllByShowIdAndTimeScheduleId(Long showId, Long timeScheduleId);
}
