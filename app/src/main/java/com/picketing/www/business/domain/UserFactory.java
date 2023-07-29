package com.picketing.www.business.domain;

import com.picketing.www.persistence.table.UserPersist;
import com.picketing.www.presentation.dto.request.user.UserSignInRequest;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;
import com.picketing.www.presentation.dto.response.user.UserSignInResponse;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFactory {
    public User create(UserPersist userPersist) {
        return User.builder()
                .email(userPersist.email())
                .password(userPersist.password())
                .name(userPersist.name())
                .phoneNumber(userPersist.phoneNumber())
                .createdAt(userPersist.createdAt())
                .modifiedAt(userPersist.modifiedAt())
                .build();
    }

    public User create(UserSignUpRequest userSignUpRequest) {
        return User.builder()
                .email(userSignUpRequest.email())
                .password(userSignUpRequest.password())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public UserPersist persist(User user) {
        return UserPersist.builder()
                .email(user.email)
                .password(user.password)
                .name(user.name)
                .phoneNumber(user.phoneNumber)
                .createdAt(user.createdAt)
                .modifiedAt(user.modifiedAt)
                .build();
    }

    public UserDetailResponse findResponse(User user) {
        return UserDetailResponse.builder()
                .email(user.email)
                .name(user.name)
                .build();
    }

    public User create(UserSignInRequest userSignInRequest) {
        return User.builder()
                .email(userSignInRequest.email())
                .password(userSignInRequest.password())
                .build();
    }

    public UserSignInResponse signInResponse(User user) {
        return UserSignInResponse.builder()
                .email(user.email)
                .build();
    }

}
