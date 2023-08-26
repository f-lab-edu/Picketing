package com.picketing.www.business.domain.schedule;

import java.time.YearMonth;

public record YearAndMonthForShow(
	YearMonth yearMonth,
	Long showId
) {
}
