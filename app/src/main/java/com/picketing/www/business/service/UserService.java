package com.picketing.www.business.service;

import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.application.exception.InvalidPasswordException;
import com.picketing.www.application.exception.UserNotFoundException;
import com.picketing.www.business.domain.User;
import com.picketing.www.business.domain.UserFactory;
import com.picketing.www.persistence.repository.UserRepository;
import com.picketing.www.persistence.table.UserPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public User login(User user) {
        UserPersist userPersist = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!user.matchPassword(userPersist.password())) {
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD);
        }

        return userFactory.create(userPersist);
    }
}
