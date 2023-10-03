package com.picketing.www.persistence.repository.reservation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
import com.picketing.www.business.domain.show.seat.SeatGrade;

public interface ScheduledShowSeatRepository extends JpaRepository<ScheduledShowSeat, Long> {

	Optional<ScheduledShowSeat> findByShowAndShowDateTimeAndSeatGrade(Long showId, String startDateTime,
		SeatGrade seatGrade);
}
