package com.picketing.www.business.domain.show.seatgrade;

import java.math.BigDecimal;

import com.picketing.www.business.domain.show.Show;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
public class SeatGrade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "SHOW_ID")
	private Show show;
	private BigDecimal price;

	public static SeatGrade createSeatGrade(Long id, String name, Show show, BigDecimal price) {
		return new SeatGrade(id, name, show, price);
	}
}
