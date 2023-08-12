package com.picketing.www.presentation.controller.schedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picketing.www.business.domain.UserFactory;
import com.picketing.www.business.service.user.UserService;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ScheduleController {

    private static final String LOGIN_USER = "login_user";
    private final UserFactory userFactory;
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDetailResponse get(@PathVariable Long userId) {
        return userFactory.findResponse(userService.get(userId));
    }
}
