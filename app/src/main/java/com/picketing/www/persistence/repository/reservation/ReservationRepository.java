package com.picketing.www.persistence.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.business.domain.reservation.ScheduledShowSeat;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	long countReservationsByShowSeat(ScheduledShowSeat scheduledShowSeat);
}
