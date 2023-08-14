package com.picketing.www.business.domain;

import java.time.LocalDateTime;

import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

public class Show {

	private Long id;
	private String title;
	private Genre genre;
	private SubGenre subGenre;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String venue;
	private Long runningTime;
	private Long intermission;
	private String details;
}
