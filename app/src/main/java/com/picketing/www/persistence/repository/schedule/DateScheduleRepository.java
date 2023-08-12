package com.picketing.www.persistence.repository.schedule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.schedule.DateSchedulePersist;

@Repository
public class DateScheduleRepository {

    private final Map<Long, DateSchedulePersist> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<DateSchedulePersist> getSchedules() {

    }
}
