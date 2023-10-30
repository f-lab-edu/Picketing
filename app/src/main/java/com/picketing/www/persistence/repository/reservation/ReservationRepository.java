package com.picketing.www.persistence.repository.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.business.domain.reservation.ScheduledShowSeat;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Long countReservationsByShowSeat(ScheduledShowSeat scheduledShowSeat);

	List<Reservation> findAllByShowSeat(ScheduledShowSeat scheduledShowSeat);

	// 예약 정보 조회 시 베타락을 건다
	@Query(value = "select r from Reservation r where r.showSeat = :scheduledShowSeat")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({
		@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")
	})
	List<Reservation> findByShowSeatWithPessimisticLock(
		@Param("scheduledShowSeat") ScheduledShowSeat scheduledShowSeat);
}
