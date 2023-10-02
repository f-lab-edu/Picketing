package com.picketing.www.business.domain.schedule;

import com.picketing.www.business.domain.show.seat.SeatGrade;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SCHEDULE_SEAT_GRADE")
public class TheaterSeatGrade {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule theaterSchedule;

	@Enumerated(EnumType.STRING)
	private SeatGrade seatGrade;
}
