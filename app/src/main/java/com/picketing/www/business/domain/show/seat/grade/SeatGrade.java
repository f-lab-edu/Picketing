package com.picketing.www.business.domain.show.seat.grade;

import java.util.ArrayList;
import java.util.List;

import com.picketing.www.business.domain.show.Show;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT_GRADES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
public class SeatGrade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "SHOW_ID")
	private Show show;

	@OneToMany(mappedBy = "seatGrade", fetch = FetchType.LAZY)
	private List<Seat> seats = new ArrayList<>();

	private Long timeScheduleId;

	private Long remainingSeatCount;
}
