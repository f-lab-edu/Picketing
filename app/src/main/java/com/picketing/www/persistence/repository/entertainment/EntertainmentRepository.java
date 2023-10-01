package com.picketing.www.persistence.repository.entertainment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.entertainment.Entertainment;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;

@Repository
public interface EntertainmentRepository extends JpaRepository<Entertainment, Long> {
	Optional<Entertainment> findEntertainmentById(Long showId);

	List<Entertainment> findEntertainmentByGenreAndSubGenre(Genre genre, SubGenre subGenre, Pageable pageable);
}
