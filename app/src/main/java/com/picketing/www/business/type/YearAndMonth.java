package com.picketing.www.business.type;

import java.time.LocalDate;

public record YearAndMonth(
	int year,
	int month
) {
	public YearAndMonth(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public YearAndMonth(LocalDate localDate) {
		this(localDate.getYear(), localDate.getMonthValue());
	}
}