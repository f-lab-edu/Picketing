package com.picketing.www.persistence.repository.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.schedule.DateSchedule;

@Repository
public interface DateScheduleRepository extends JpaRepository<DateSchedule, Long> {

}
