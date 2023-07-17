package com.picketing.www.business.domain;

import com.picketing.www.persistence.table.UserPersist;
import com.picketing.www.presentation.dto.request.user.UserSignInRequest;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;
import com.picketing.www.presentation.dto.response.user.UserSignInResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFactory {

    public User create(UserPersist userPersist) {
        return new User(
                userPersist.email(),
                userPersist.password(),
                userPersist.name(),
                userPersist.phoneNumber(),
                userPersist.createdAt(),
                userPersist.modifiedAt()
            );
    }

    public User create(UserSignUpRequest userSignUpRequest) {
        return new User(
                userSignUpRequest.email(),
                userSignUpRequest.password(),
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public UserPersist persist(User user) {
        return new UserPersist(
                user.email,
                user.password,
                user.name,
                user.phoneNumber,
                user.createdAt,
                user.modifiedAt
        );
    }

    public UserDetailResponse findResponse(User user) {
        return new UserDetailResponse(
                user.email,
                user.name
        );
    }

    public User create(UserSignInRequest userSignInRequest) {
        return new User(
                userSignInRequest.email(),
                userSignInRequest.password(),
                null,
                null,
                null,
                null
        );
    }

    public UserSignInResponse signInResponse(User user) {
        return new UserSignInResponse(
                user.email
        );
    }

}
