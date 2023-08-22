package com.picketing.www.persistence.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.table.ShowPersist;

@Repository
public class ShowRepository {

	private final Map<Long, ShowPersist> showDB = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1L);
	private static final String GENRE_ALL = "ALL";

	public List<ShowPersist> findShowListWithPagination(String genre, String subGenre, Pageable pageable) {
		if (GENRE_ALL.equals(genre)) {
			return new ArrayList<>(showDB.values());
		} else if (GENRE_ALL.equals(subGenre)) {
			return showDB.values()
				.stream()
				.filter(s -> s.genre().toString().equals(genre))
				.collect(Collectors.toList());
		} else {
			return showDB.values()
				.stream()
				.filter(s -> s.genre().toString().equals(genre) && s.subGenre().toString().equals(subGenre))
				.collect(Collectors.toList());
		}
	}

	public ShowPersist save() {
		long currentId = sequence.incrementAndGet();
		showDB.put(currentId, ShowPersist.builder()
			.id(currentId)
			.title("찰리 푸스 내한 공연")
			.genre(Genre.CONCERT)
			.subGenre(SubGenre.FOREIGN)
			.startDate(LocalDate.of(2023, 10, 20))
			.endDate(LocalDate.of(2023, 10, 22))
			.venue("KSPO DOME(올림픽체조경기장)")
			.runningTime(70L)
			.intermission(0L)
			.ageGroup(AgeGroup.SEVEN)
			.details("찰리 푸스 내한공연 (Charlie Puth Live in Seoul")
			.isBookable(true)
			.ownerId(1L)
			.createdAt(LocalDateTime.now())
			.modifiedAt(null)
			.build());

		currentId = sequence.incrementAndGet();
		showDB.put(currentId, ShowPersist.builder()
			.id(currentId)
			.title("라우브 내한 공연")
			.genre(Genre.CONCERT)
			.subGenre(SubGenre.FOREIGN)
			.startDate(LocalDate.of(2023, 8, 29))
			.endDate(LocalDate.of(2023, 8, 29))
			.venue("KSPO DOME(올림픽체조경기장)")
			.runningTime(80L)
			.intermission(0L)
			.ageGroup(AgeGroup.NINE)
			.details("라우브 내한공연 (LAUV Live in Seoul")
			.isBookable(true)
			.ownerId(1L)
			.createdAt(LocalDateTime.now())
			.modifiedAt(null)
			.build());

		currentId = sequence.incrementAndGet();
		showDB.put(currentId, ShowPersist.builder()
			.id(currentId)
			.title("2023 김동률 콘서트 'Melody'")
			.genre(Genre.CONCERT)
			.subGenre(SubGenre.DOMESTIC)
			.startDate(LocalDate.of(2023, 10, 7))
			.endDate(LocalDate.of(2023, 10, 15))
			.venue("KSPO DOME(올림픽체조경기장)")
			.runningTime(150L)
			.intermission(0L)
			.ageGroup(AgeGroup.NINE)
			.details("2023 김동률 콘서트 'Melody'")
			.isBookable(true)
			.ownerId(2L)
			.createdAt(LocalDateTime.now())
			.modifiedAt(null)
			.build());

		currentId = sequence.incrementAndGet();
		showDB.put(currentId, ShowPersist.builder()
			.id(currentId)
			.title("뮤지컬 <레베카> 10주년 기념공연")
			.genre(Genre.MUSICAL)
			.subGenre(SubGenre.LICENSE)
			.startDate(LocalDate.of(2023, 8, 19))
			.endDate(LocalDate.of(2023, 11, 19))
			.venue("블루스퀘어 신한카드홀")
			.runningTime(175L)
			.intermission(20L)
			.ageGroup(AgeGroup.NINE)
			.details("뮤지컬 <레베카> 10주년 기념공연")
			.isBookable(true)
			.ownerId(3L)
			.createdAt(LocalDateTime.now())
			.modifiedAt(null)
			.build());

		currentId = sequence.incrementAndGet();
		showDB.put(currentId, ShowPersist.builder()
			.id(currentId)
			.title("뮤지컬 <모차르트!>")
			.genre(Genre.MUSICAL)
			.subGenre(SubGenre.LICENSE)
			.startDate(LocalDate.of(2023, 6, 15))
			.endDate(LocalDate.of(2023, 8, 22))
			.venue("세종문화회관 대극장")
			.runningTime(175L)
			.intermission(20L)
			.ageGroup(AgeGroup.NINE)
			.details("뮤지컬 <모차르트!>")
			.isBookable(false)
			.ownerId(4L)
			.createdAt(LocalDateTime.now())
			.modifiedAt(null)
			.build());

		return showDB.get(1L);
	}

}
