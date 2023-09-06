package com.picketing.www.persistence.repository.show;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.picketing.www.persistence.table.ShowPersist;

public interface ShowRepository {
	List<ShowPersist> findShowListWithPagination(String genre, String subGenre, Pageable pageable);

	ShowPersist save(ShowPersist showPersist);

	boolean notExistShow(Long showId);
}
