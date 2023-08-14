package com.picketing.www.persistence.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.UserPersist;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final Map<Long, UserPersist> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);

	// 사용자 이메일 조회 성능 향상을 위한 인덱스
	private final Map<String, UserPersist> userEmailIndex = new ConcurrentHashMap<>();

	public Long save(UserPersist userPersist) {
		long id = sequence.get();
		store.put(id, userPersist);
		sequence.set(id + 1);
		userEmailIndex.put(userPersist.email(), userPersist);

		return id;
	}

	public Boolean existByEmail(String email) {
		for (Map.Entry<Long, UserPersist> userPersistEntry : store.entrySet()) {
			if (email.equals(userPersistEntry.getValue().email())) {
				return true;
			}
		}
		return false;
	}

	public UserPersist findById(Long userId) {
		return store.get(userId);
	}

	public Optional<UserPersist> findByEmail(String email) {
		return Optional.of(userEmailIndex.get(email));
	}
}
