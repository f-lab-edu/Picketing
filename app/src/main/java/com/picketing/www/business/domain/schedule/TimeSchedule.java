package com.picketing.www.business.domain.schedule;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TIME_SCHEDULE")
@Getter(AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class TimeSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TIME_SCHEDULE_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DATE_SCHEDULE_ID")
	private DateSchedule dateSchedule;
	
	private String name;
	private LocalTime startTime;
	private LocalTime endTime;

	public TimeSchedule addShowDateSchedule(DateSchedule dateSchedule) {
		this.dateSchedule = dateSchedule;
		dateSchedule.getTimeSchedules().add(this);
		return this;
	}
}
