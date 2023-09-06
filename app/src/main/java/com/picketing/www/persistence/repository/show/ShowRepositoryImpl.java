package com.picketing.www.persistence.repository.show;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.SeatGradePersist;
import com.picketing.www.persistence.table.ShowPersist;

@Repository
public class ShowRepositoryImpl implements ShowRepository, SeatGradeRepository {

	private static final String GENRE_ALL = "ALL";

	private final Map<Long, ShowPersist> showDB = new ConcurrentHashMap<>();
	private final Map<Long, SeatGradePersist> seatGradeStore;

	private final AtomicLong sequence = new AtomicLong(0L);

	private final Map<Long, List<SeatGradePersist>> showIdIndex;

	public ShowRepositoryImpl(Map<Long, SeatGradePersist> seatGradeStore) {
		this.seatGradeStore = seatGradeStore;
		this.showIdIndex = new ConcurrentHashMap<>();
		seatGradeStore.forEach((seatGradeId, seatGradePersist) -> {
			List<SeatGradePersist> indexRecode = showIdIndex.getOrDefault(seatGradePersist.showId(), new ArrayList<>());
			indexRecode.add(seatGradePersist);
			showIdIndex.put(seatGradePersist.showId(), indexRecode);
		});
	}

	@Override
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

	@Override
	public ShowPersist save(ShowPersist showPersist) {
		showPersist.createShowPersist(sequence.incrementAndGet());
		showDB.putIfAbsent(showPersist.getId(), showPersist);
		return showPersist;
	}

	@Override
	public boolean notExistShow(Long showId) {
		return Objects.isNull(showDB.get(showId));
	}

	@Override
	public List<SeatGradePersist> findSeatGradeByShowIdAndTime(Long showId, Long scheduleId) {
		return showIdIndex.get(showId);
	}
}
