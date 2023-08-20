package com.picketing.www.persistence.table.schedule;

import java.time.LocalDate;

public record DateSchedulePersist(
	Long id,
	Long showId,
	String name,
	LocalDate startDate,
	LocalDate endDate
) {
}
