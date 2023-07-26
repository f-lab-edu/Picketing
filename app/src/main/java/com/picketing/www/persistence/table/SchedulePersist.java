package com.picketing.www.persistence.table;

import java.time.LocalDate;
import java.time.LocalTime;

public record SchedulePersist(
        Long id,
        Long concertId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        LocalTime startTime,
        LocalTime endTime
) { }
