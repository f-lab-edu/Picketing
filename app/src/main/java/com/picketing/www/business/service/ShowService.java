package com.picketing.www.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.Show;
import com.picketing.www.business.domain.ShowFactory;
import com.picketing.www.persistence.repository.ShowRepository;
import com.picketing.www.persistence.table.ShowPersist;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowRepository showRepository;

	private final ShowFactory showFactory;

	public List<Show> getShowList(String genre, String subGenre, Pageable pageable) {
		List<ShowPersist> showPersistList = showRepository.findShowPersistsByGenreAndSubGenre(genre, subGenre,
			pageable);
		return showPersistList.stream()
			.map(showFactory::convertToEntity)
			.collect(Collectors.toList());
	}
}
