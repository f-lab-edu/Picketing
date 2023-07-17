package com.picketing.www.persistence.repository;

import com.picketing.www.persistence.table.UserPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final Map<Long, UserPersist> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public Long save(UserPersist userPersist) {
        long id = sequence.get();
        store.put(id, userPersist);
        sequence.set(id + 1);
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
        return store.values()
                .stream()
                .filter(u -> u.email().equals(email))
                .findFirst();
    }
}
