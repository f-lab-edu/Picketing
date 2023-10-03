package com.picketing.www.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picketing.www.business.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findUserById(Long id);

	Optional<User> findUserByEmail(String email);

	boolean existsByEmail(String email);
}
