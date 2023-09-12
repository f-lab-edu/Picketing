package com.picketing.www.business.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.Show;
import com.picketing.www.business.domain.ShowFactory;
import com.picketing.www.persistence.repository.ShowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowRepository showRepository;

	private final ShowFactory showFactory;

	public List<Show> getShowList(String genre, String subGenre, Pageable pageable) {
		return showRepository.findShowPersistsByGenreAndSubGenre(genre, subGenre,
			pageable);
	}
}
