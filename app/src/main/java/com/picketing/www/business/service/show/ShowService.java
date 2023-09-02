package com.picketing.www.business.service.show;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.business.domain.show.SeatGradeFactory;
import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.ShowFactory;
import com.picketing.www.persistence.repository.show.ShowRepository;
import com.picketing.www.persistence.table.ShowPersist;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowRepository showRepository;

	private final ShowFactory showFactory;
	private final SeatGradeFactory seatGradeFactory;

	public List<Show> getShowList(String genre, String subGenre, Pageable pageable) {
		List<ShowPersist> showPersistList = showRepository.findShowListWithPagination(genre, subGenre, pageable);
		return showPersistList.stream()
			.map(showFactory::convertToEntity)
			.collect(Collectors.toList());
	}

	public void checkExistShow(Long showId) {
		if (showRepository.notExistShow(showId)) {
			throw new CustomException(ErrorCode.SHOW_NOT_FOUND);
		}
	}
}
