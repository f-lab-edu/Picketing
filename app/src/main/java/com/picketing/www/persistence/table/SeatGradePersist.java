package com.picketing.www.persistence.table;

import java.math.BigDecimal;

public record SeatGradePersist(
	Long id,
	Long showId,
	String name,
	BigDecimal price
) {
}
