package com.picketing.www.persistence.repository.reservation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seat.SeatGrade;

@Repository
public interface ScheduledShowSeatRepository extends JpaRepository<ScheduledShowSeat, Long> {

	Optional<ScheduledShowSeat> findScheduledShowSeatByShowAndShowDateTimeAndSeatGrade(Show show,
		LocalDateTime startDateTime,
		SeatGrade seatGrade);
}
