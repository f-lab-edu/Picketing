package com.picketing.www.business.service.reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.persistence.repository.reservation.ScheduledShowSeatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduledShowSeatService {

	private final ScheduledShowSeatRepository scheduledShowSeatRepository;

	public ScheduledShowSeat getScheduledShowSeat(Show show, String showTime, SeatGrade seatGrade) {
		return scheduledShowSeatRepository.findScheduledShowSeatByShowAndShowDateTimeAndSeatGrade(show,
				LocalDateTime.parse(showTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
				seatGrade)
			.orElseThrow(() -> new CustomException(ErrorCode.SHOW_NOT_FOUND));
	}
}
