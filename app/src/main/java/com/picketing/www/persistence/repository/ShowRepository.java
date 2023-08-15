package com.picketing.www.persistence.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.ShowPersist;

@Repository
public class ShowRepository {

	private final Map<Long, ShowPersist> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1L);
}
