package com.picketing.www.persistence.repository.show;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.show.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

	List<Show> findShowsByGenreAndSubGenre(String genre, String subGenre, Pageable pageable);
}