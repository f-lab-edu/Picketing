package com.picketing.www.business.service.show;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.ShowFactory;
import com.picketing.www.business.domain.show.ShowSeatGrade;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.repository.show.ShowRepository;
import com.picketing.www.persistence.table.ShowPersist;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowFactory showFactory;
	private final ShowRepository showRepository;
	private final SeatGradeService seatGradeService;

	public List<Show> getShowList(String genre, String subGenre, Pageable pageable) {
		List<ShowPersist> showPersistList = showRepository.findShowPersistsByGenreAndSubGenre(
			Genre.valueOf(genre), SubGenre.valueOf(subGenre),
			pageable);
		return showPersistList.stream()
			.map(showFactory::convertToEntity)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ShowSeatGrade> getRemainingSeatsByShowAndSchedule(Long showId, Long scheduleId) {
		checkExistShow(showId);
		return seatGradeService.gets(showId, scheduleId);
	}

	private void checkExistShow(Long showId) {
		if (showRepository.existsById(showId)) {
			throw new CustomException(ErrorCode.SHOW_NOT_FOUND);
		}
	}
}
