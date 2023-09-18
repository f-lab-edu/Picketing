package com.picketing.www.persistence.repository.show.seatgrade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seatgrade.SeatGrade;

@Repository
public interface SeatGradeRepository extends JpaRepository<SeatGrade, Long> {

	@Query("select sg from SeatGrade sg where sg.show = :show")
	List<SeatGrade> findAllByShow(@Param("show") Show show);
}
