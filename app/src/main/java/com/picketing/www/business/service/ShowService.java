package com.picketing.www.business.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.picketing.www.business.domain.Show;
import com.picketing.www.business.domain.ShowFactory;
import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.repository.ShowRepository;
import com.picketing.www.persistence.table.ShowPersist;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShowService {

	private final ShowRepository showRepository;

	private final ShowFactory showFactory;

	public List<Show> getShowList(String genre, String subGenre, Pageable pageable) {
		createShow();
		List<ShowPersist> showPersistList = showRepository.findShowListWithPagination(genre, subGenre, pageable);
		return showPersistList.stream()
			.map(showFactory::convertToEntity)
			.collect(Collectors.toList());
	}

	public void createShow() {
		Show build = Show.builder()
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
			.build();
		showRepository.save(showFactory.create(build));

		Show build2 = Show.builder()
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
			.build();
		showRepository.save(showFactory.create(build2));

		Show build3 = Show.builder()
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
			.build();
		showRepository.save(showFactory.create(build3));

		Show build4 = Show.builder()
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
			.build();
		showRepository.save(showFactory.create(build4));

		Show build5 = Show.builder()
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
			.build();
		showRepository.save(showFactory.create(build5));

		Show build6 = Show.builder()
			.title("야구 삼성 vs 한화")
			.genre(Genre.SPORT)
			.subGenre(SubGenre.BASEBALL)
			.startDate(LocalDate.of(2023, 5, 5))
			.endDate(LocalDate.of(2023, 5, 5))
			.venue("삼성 라이온즈 파크")
			.runningTime(180L)
			.intermission(10L)
			.ageGroup(AgeGroup.ALL)
			.details("야구 경기")
			.isBookable(false)
			.ownerId(4L)
			.build();
		showRepository.save(showFactory.create(build6));
	}
}
