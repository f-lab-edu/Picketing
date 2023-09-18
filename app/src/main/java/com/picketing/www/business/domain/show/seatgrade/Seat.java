package com.picketing.www.business.domain.show.seatgrade;

import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SEAT_GRADE_ID")
	private SeatGrade seatGrade;

	// TODO 23.09.13: Time schedule id mapping. @ManyToOne
	private Long timeScheduleId;
}
