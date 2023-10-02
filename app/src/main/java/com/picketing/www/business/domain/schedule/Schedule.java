package com.picketing.www.business.domain.schedule;

import java.util.ArrayList;
import java.util.List;

import com.picketing.www.business.domain.TheaterSeatGrade;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "SCHEDULE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "show_id")
	private Show show;

	// 공연장 정보
	@OneToMany(mappedBy = "theaterSchedule")
	private List<TheaterSeatGrade> theaterSeatGrades = new ArrayList<>(); // 공연장 정보

	@OneToMany(mappedBy = "showSchedule")
	private List<DateSchedule> dateSchedules = new ArrayList<>(); // 날짜 스케줄
}
