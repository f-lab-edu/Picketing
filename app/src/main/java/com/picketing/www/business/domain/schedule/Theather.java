package com.picketing.www.business.domain.schedule;

import com.picketing.www.business.domain.show.seat.SeatGrade;
import lombok.Builder;

import java.util.List;

public class Theather {
	long theaterId;
	List<SeatGrade> dateSchedules;
}
