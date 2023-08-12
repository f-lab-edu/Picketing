package com.picketing.www.business.domain;

import java.time.LocalDate;
import java.util.List;

public class DateSchedule {
    private Long id;
	private Long showId;
	private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TimeSchedule> timeSchedules;
}
