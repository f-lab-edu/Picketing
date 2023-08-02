package com.picketing.www.business.domain;

import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

import java.time.LocalDateTime;

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
