package com.picketing.www.persistence.table;

import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import java.time.LocalDateTime;

public record ShowPersist(
        Long id,
        String title,
        Genre genre,
        SubGenre subGenre,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String venue,
        Long runningTime,
        Long intermission,
        String details
) {
}