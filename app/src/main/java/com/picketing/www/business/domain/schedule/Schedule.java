package com.picketing.www.business.domain.schedule;

import java.util.ArrayList;
import java.util.List;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "SCHEDULE")
public class Schedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Show show;

	// // private Theater theater;
	// // TODO 매핑 테이블로 빼야함
	// private List<SeatGrade> seatGradeList; // 공연장 정보

	// FIXME 다시 점검
	// TODO 공연장 정보
	@OneToMany(mappedBy = "schedule")
	private List<ScheduleSeatGrade> scheduleSeatGradeList = new ArrayList<>(); // 공연장 정보

	@OneToMany(mappedBy = "schedule")
	private List<DateSchedule> dateSchedules; // 날짜 스케줄
}
