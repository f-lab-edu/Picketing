package com.picketing.www.presentation.controller.user;

import com.picketing.www.business.domain.User;
import com.picketing.www.business.domain.UserFactory;
import com.picketing.www.business.service.UserService;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import com.picketing.www.presentation.dto.response.IdentityResponse;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFactory userFactory;
    private final UserService userService;

    @PostMapping
    public IdentityResponse create(@RequestBody UserSignUpRequest userSignUpRequest) {
        User user = userFactory.create(userSignUpRequest);
        Long id = userService.create(user);
        return new IdentityResponse(id);
    }

    @GetMapping("/{userId}")
    public UserDetailResponse get(@PathVariable Long userId) {
        return userFactory.findResponse(userService.get(userId));
    }
}
