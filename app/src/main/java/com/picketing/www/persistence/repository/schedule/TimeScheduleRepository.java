package com.picketing.www.persistence.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.schedule.TimeSchedule;

@Repository
public interface TimeScheduleRepository extends JpaRepository<TimeSchedule, Long> {
}
