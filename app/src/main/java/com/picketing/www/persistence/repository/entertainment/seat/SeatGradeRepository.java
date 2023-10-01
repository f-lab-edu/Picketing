package com.picketing.www.persistence.repository.entertainment.seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.entertainment.seat.SeatGrade;

@Repository
public interface SeatGradeRepository extends JpaRepository<SeatGrade, Long> {

	@Query("select sg"
		+ " from SeatGrade sg inner join Seat s on sg.seat.id = s.id where s.show.id = :showId")
	List<SeatGrade> findAllByShow(@Param("showId") Long showId);
}
