package com.picketing.www.persistence.repository.seat;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.SeatPersist;

@Repository
public class SeatRepository {

	private final Map<Long, SeatPersist> seatStore;

	public SeatRepository(Map<Long, SeatPersist> seatStore) {
		this.seatStore = seatStore;
	}

}
