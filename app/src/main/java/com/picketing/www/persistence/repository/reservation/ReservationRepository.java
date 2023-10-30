package com.picketing.www.persistence.repository.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.business.domain.reservation.ScheduledShowSeat;

import jakarta.persistence.LockModeType;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Long countReservationsByShowSeat(ScheduledShowSeat scheduledShowSeat);

	List<Reservation> findAllByShowSeat(ScheduledShowSeat scheduledShowSeat);

	@Lock(LockModeType.OPTIMISTIC)
	@Query(value = "select r from Reservation r where r.showSeat = :scheduledShowSeat")
	List<Reservation> findByShowSeatWithOptimisticLock(@Param("scheduledShowSeat") ScheduledShowSeat scheduledShowSeat);
}
