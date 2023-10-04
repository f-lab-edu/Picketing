package com.picketing.www.business.service.show;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.reservation.Reservation;
import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
import com.picketing.www.business.domain.reservation.ScheduledShowSeatFactory;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seat.SeatGrade;
import com.picketing.www.business.service.reservation.ReservationService;
import com.picketing.www.business.service.reservation.ScheduledShowSeatService;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.repository.show.ShowRepository;
import com.picketing.www.presentation.dto.response.show.seat.RemainingSeatsResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowRepository showRepository;
	private final ReservationService reservationService;
	private final ScheduledShowSeatService scheduledShowSeatService;

	private final ScheduledShowSeatFactory scheduledShowSeatFactory;

	// TODO 카테고리에 따른 공연 목록 조회 로직 구현
	public List<Show> getShowList(Genre genre, SubGenre subGenre, Pageable pageable) {
		// return showRepository.findShowsByGenreAndSubGenre(genre, subGenre, pageable);
		return new ArrayList<>();
	}

	// TODO 공연의 좌석 등급 목록 조회 로직 구현
	public List<SeatGrade> getShowSeatGradeList(Long showId) {
		// showRepository.findShowById(showId)
		// 	.orElseThrow(() -> new CustomException(ErrorCode.SHOW_NOT_FOUND));
		// return seatGradeService.getSeatGradeList(showId);
		return new ArrayList<>();
	}

	public Show getShowById(Long showId) {
		return showRepository.findById(showId)
			.orElseThrow(() -> new CustomException(ErrorCode.SHOW_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public List<RemainingSeatsResponse.RemainingSeatDetail> getRemainingSeats(Long showId, String showTime) {
		// 공연의 등급별 좌석 리스트
		List<ScheduledShowSeat> scheduledShowSeatList = scheduledShowSeatService.getScheduledShowSeatList(
			getShowById(showId), showTime);

		scheduledShowSeatService.getScheduledShowSeatList()

		scheduledShowSeatList.forEach(showSeat -> {
			// 예약된 좌석 리스트
			List<Reservation> reservedSeatList = reservationService.getReservationsByShowSeat(showSeat);
		});
	}

}
