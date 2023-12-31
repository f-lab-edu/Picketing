package com.picketing.www.persistence.repository.show;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.show.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	Optional<Show> findById(Long showId);

	// TODO 카테고리에 따른 공연 목록 조회 쿼리 구현
	// List<Show> findShowsByGenreAndSubGenre(Genre genre, SubGenre subGenre, Pageable pageable);
}
