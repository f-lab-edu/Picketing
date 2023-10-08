package com.picketing.www.business.service.reservation;

import java.time.LocalDateTime;
import java.util.List;

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

	public ScheduledShowSeat getScheduledShowSeat(Show show, LocalDateTime showTime, SeatGrade seatGrade) {
		return scheduledShowSeatRepository.findScheduledShowSeatByShowAndShowDateTimeAndSeatGrade(show, showTime,
				seatGrade)
			.orElseThrow(() -> new CustomException(ErrorCode.SHOW_NOT_FOUND));
	}

	public List<ScheduledShowSeat> getScheduledShowSeatList(Show show, LocalDateTime showTime) {
		return scheduledShowSeatRepository.findAllByShowAndShowDateTime(show, showTime);
	}
}
