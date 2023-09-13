package com.picketing.www.business.service.show;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.persistence.repository.show.ShowRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowRepository showRepository;

	public List<Show> getShowList(String genre, String subGenre, Pageable pageable) {
		return showRepository.findShowsByGenreAndSubGenre(genre, subGenre, pageable);
	}
}
