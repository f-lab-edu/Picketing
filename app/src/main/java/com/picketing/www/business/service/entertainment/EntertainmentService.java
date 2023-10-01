package com.picketing.www.business.service.entertainment;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.entertainment.Entertainment;
import com.picketing.www.business.domain.entertainment.seat.SeatGrade;
import com.picketing.www.business.service.entertainment.seat.SeatGradeService;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.repository.entertainment.EntertainmentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EntertainmentService {

	private final EntertainmentRepository entertainmentRepository;

	private final SeatGradeService seatGradeService;

	public List<Entertainment> getShowList(Genre genre, SubGenre subGenre, Pageable pageable) {
		return entertainmentRepository.findShowsByGenreAndSubGenre(genre, subGenre, pageable);
	}

	public List<SeatGrade> getShowSeatGradeList(Long showId) {
		entertainmentRepository.findShowById(showId)
			.orElseThrow(() -> new CustomException(ErrorCode.SHOW_NOT_FOUND));
		return seatGradeService.getSeatGradeList(showId);
	}
}
