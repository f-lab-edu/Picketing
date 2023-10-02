package com.picketing.www.business.domain.schedule;

import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TIME_SCHEDULE_SEAT_GRADE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeScheduleSeatGrade extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_schedule_id")
	private TimeSchedule timeSchedule;

	@Enumerated(EnumType.STRING)
	private SeatGrade seatGrade; // 현재 좌석 등급 및 가격이 모두 고정이므로, 같은 SeatGrade Enum 객체를 사용
}
