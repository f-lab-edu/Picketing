package com.picketing.www.business.domain.schedule;

import java.util.List;

import com.picketing.www.business.domain.show.seat.SeatGrade;

import lombok.Builder;

public class Schedule {

	private Long showId;

	// TODO 매핑 테이블로 빼야함
	private List<SeatGrade> seatGradeList; // 공연장 정보

	List<DateSchedule> dateSchedules;

	@Builder
	public Schedule(List<DateSchedule> dateSchedules) {
		this.dateSchedules = dateSchedules;
	}
}
