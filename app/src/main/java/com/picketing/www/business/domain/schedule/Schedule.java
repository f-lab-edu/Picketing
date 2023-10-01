package com.picketing.www.business.domain.schedule;

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

	private Hall hall;

	@OneToMany(mappedBy = "schedule")
	private List<DateSchedule> dateSchedules;
}
