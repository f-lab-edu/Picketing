package com.picketing.www.persistence.repository.show.seatgrade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picketing.www.business.domain.show.seatgrade.SeatGrade;

public interface SeatGradeRepository extends JpaRepository<SeatGrade, Long> {
}
