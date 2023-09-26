package com.picketing.www.persistence.repository.show.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.show.seat.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
