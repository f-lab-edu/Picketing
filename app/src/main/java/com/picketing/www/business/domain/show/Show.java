package com.picketing.www.business.domain.show;

import java.time.LocalDate;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Builder(access = AccessLevel.PROTECTED)
@Table(name = "SHOWS")
@Getter(AccessLevel.PROTECTED)
public class Show extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	private String title;

	@Enumerated(EnumType.STRING)
	private Genre genre;
	@Enumerated(EnumType.STRING)
	private SubGenre subGenre;
	private LocalDate startDate;
	private LocalDate endDate;
	private String venue;
	private Long runningTime;
	private Long intermission;

	@Enumerated(EnumType.STRING)
	private AgeGroup ageGroup;
	private String details;
	private boolean isBookable;
	private Long ownerId;
}
