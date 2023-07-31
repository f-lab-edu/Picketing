package com.picketing.www.business.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.application.exception.InvalidPasswordException;
import com.picketing.www.application.exception.UserNotFoundException;
import com.picketing.www.business.domain.User;
import com.picketing.www.business.domain.UserFactory;
import com.picketing.www.persistence.repository.UserRepository;
import com.picketing.www.persistence.table.UserPersist;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    private static final String SESSION_LOGIN_USER = "login_user";

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

        User loginUser = userFactory.create(userPersist);

        if (!user.match(loginUser)) {
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD);
        }

        httpSession.setAttribute(SESSION_LOGIN_USER, loginUser.getEmail());

        return loginUser;
    }
}
