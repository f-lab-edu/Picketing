package com.picketing.www.business.domain.show.seat;

import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THEATER_SEAT_GRADE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
public class TheaterSeatGrade extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;
	//
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "schedule_id")
	// private Schedule theaterSchedule;

	@Enumerated(EnumType.STRING)
	private SeatGrade seatGrade;
}
