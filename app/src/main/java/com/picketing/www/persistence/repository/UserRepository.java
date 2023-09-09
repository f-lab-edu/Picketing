package com.picketing.www.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.persistence.table.UserPersist;

@Repository
public interface UserRepository extends JpaRepository<UserPersist, Long> {

	Optional<UserPersist> findById(Long id);

	Optional<UserPersist> findByEmail(String eamil);

	boolean existsByEmail(String email);
}
