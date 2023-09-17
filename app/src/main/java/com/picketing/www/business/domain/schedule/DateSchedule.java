package com.picketing.www.business.domain.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "DATE_SCHEDULE")
@Getter(AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
public class DateSchedule extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DATE_SCHEDULE_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SHOW_ID")
	private Show show;

	private String name;

	private LocalDate startDate;

	private LocalDate endDate;

	@OneToMany(mappedBy = "dateSchedule")
	private List<TimeSchedule> timeSchedules = new ArrayList<>();
}
