package com.picketing.www.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.ShowPersist;

@Repository
public class ShowRepository {

	private final Map<Long, ShowPersist> showDB = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0L);
	private static final String GENRE_ALL = "ALL";

	public List<ShowPersist> findShowListWithPagination(String genre, String subGenre, Pageable pageable) {
		if (GENRE_ALL.equals(genre)) {
			return new ArrayList<>(showDB.values());
		} else if (GENRE_ALL.equals(subGenre)) {
			return showDB.values()
				.stream()
				.filter(s -> s.getGenre().toString().equals(genre))
				.collect(Collectors.toList());
		} else {
			return showDB.values()
				.stream()
				.filter(s -> s.getGenre().toString().equals(genre) && s.getSubGenre().toString().equals(subGenre))
				.collect(Collectors.toList());
		}
	}

	public ShowPersist save(ShowPersist showPersist) {
		showPersist.createShowPersist(sequence.incrementAndGet());
		showDB.putIfAbsent(showPersist.getId(), showPersist);

		return showPersist;
	}

}
