package com.picketing.www.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.ShowPersist;

@Repository
public class ShowRepository {

	private final Map<Long, ShowPersist> showDB = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1L);

	public List<ShowPersist> findShowListWithPagination(Pageable pageable) {
		return new ArrayList<>(showDB.values());
	}

}
