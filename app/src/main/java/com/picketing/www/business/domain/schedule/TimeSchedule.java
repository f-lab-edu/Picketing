package com.picketing.www.business.domain.schedule;

import java.time.LocalTime;
import java.util.List;

import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.business.domain.show.seat.SeatGradeEnum;
import lombok.Builder;

public class TimeSchedule {
    final Long id;
    final Long dateScheduleId;
    final LocalTime startTime;

    List<SeatGradeEnum> seatGrades;
    @Builder
    public TimeSchedule(Long id, Long dateScheduleId, LocalTime startTime) {
        this.id = id;
        this.dateScheduleId = dateScheduleId;
        this.startTime = startTime;
    }
}
