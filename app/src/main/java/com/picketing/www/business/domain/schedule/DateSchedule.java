package com.picketing.www.business.domain.schedule;

import java.time.LocalDate;
import java.util.List;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DATE_SCHEDULE")
@Getter(AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class DateSchedule extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// TODO 연관관계 매핑
	private Show show;

	private String name;
	private LocalDate startDate;
	private LocalDate endDate;

	// TODO 연관관계 매핑
	List<TimeSchedule> timeSchedules;
}
