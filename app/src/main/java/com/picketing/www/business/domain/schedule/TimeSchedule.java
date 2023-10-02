package com.picketing.www.business.domain.schedule;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Table(name = "TIME_SCHEDULE")
@Getter(AccessLevel.PROTECTED)
public class TimeSchedule extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "date_schedule_id")
	private DateSchedule dateSchedule;

	private LocalTime startTime;

	@OneToMany(mappedBy = "timeSchedule")
	private List<TimeScheduleSeatGrade> timeScheduleSeatGradeList = new ArrayList<>();
}
