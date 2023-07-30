package com.picketing.www.business.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.business.domain.User;
import com.picketing.www.business.domain.UserFactory;
import com.picketing.www.persistence.repository.UserRepository;
import com.picketing.www.persistence.table.UserPersist;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserFactory userFactory;
	private final UserRepository userRepository;

	public Long create(User user) {
		String email = user.getEmail();
		if (userRepository.existByEmail(email)) {
			throw new BadRequestException("Duplicated email");
		}
		UserPersist persist = userFactory.persist(user);
		return userRepository.save(persist);
	}

	public User get(Long userId) {
		UserPersist persist = userRepository.findById(userId);
		return userFactory.create(persist);
	}
}
