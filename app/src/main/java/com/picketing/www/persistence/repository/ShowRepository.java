package com.picketing.www.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.ShowPersist;

@Repository
public interface ShowRepository extends JpaRepository<ShowPersist, Long> {

	List<ShowPersist> findShowPersistsByGenreAndsAndSubGenre(String genre, String subGenre, Pageable pageable);
}
