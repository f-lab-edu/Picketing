package com.picketing.www.persistence.repository.show;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.table.ShowPersist;

public interface ShowRepository extends JpaRepository<ShowPersist, Long> {

	List<ShowPersist> findShowPersistsByGenreAndSubGenre(Genre genre, SubGenre subGenre, Pageable pageable);

	boolean existsById(Long id);
}
