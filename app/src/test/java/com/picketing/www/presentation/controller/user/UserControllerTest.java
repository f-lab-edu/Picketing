package com.picketing.www.presentation.controller.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picketing.www.persistence.repository.UserRepository;
import com.picketing.www.persistence.table.UserPersist;
import com.picketing.www.presentation.dto.request.user.UserSignInRequest;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"PASSWORD_SALT=1234qwer"})
class UserControllerTest {

    private static final String BASE_PATH = "/api/users";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    @DisplayName("User 단일 조회")
    public class GetUser {

        @Test
        @DisplayName("200:존재하는 데이터 정상 조회")
        void success() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .get(BASE_PATH + "/1")
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("404:존재하지 않는 데이터")
        void failedBecauseNotFound() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/users/99999")
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("User 등록")
    public class CreateUser {

        @Test
        @DisplayName("200:정상 등록")
        void success() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "test@email.com", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:이메일 중복")
        void failedBecauseDuplicateEmail() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "test@email.com", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:안전하지 않은 비밀번호")
        void failedBecauseNotSafetyPassword() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "test@email.com", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:올바르지 않은 이메일 형식(@가 존재하지 않음)")
        void failedBecauseInvalidEmailFormatAtIsNotExist() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "testemail.com", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:올바르지 않은 이메일 형식(.이 존재하지 않음)")
        void failedBecauseInvalidEmailFormatDotIsNotExist() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "test@emailcom", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:올바르지 않은 이메일 형식(Domain 이 존재하지 않음)")
        void failedBecauseInvalidEmailFormatDomainIsNotExist() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "test@", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:올바르지 않은 이메일 형식(Local 이 존재하지 않음)")
        void failedBecauseInvalidEmailFormatLocalIsNotExist() throws Exception {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                "@email.com", "password1234@"
            );

            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignUpRequest))
                )
                .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("User 로그인")
    public class SignInUser {

        @MockBean
        UserRepository userRepository;

        @BeforeEach
        void setUpTestUser() {
            Mockito.when(userRepository.findByEmail(Mockito.any()))
                .thenReturn(Optional.of(new UserPersist(
                    "test@email.com",
                    "test1234!",
                    "testUser",
                    "01012345678",
                    LocalDateTime.now(),
                    LocalDateTime.now()
                )));
        }

        @Test
        @DisplayName("200:로그인 성공")
        void success() throws Exception {
            UserSignInRequest userSignInRequest = new UserSignInRequest(
                "test@email.com",
                "test1234!"
            );
            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH + "/signin")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignInRequest))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("400:비밀번호가 일치하지 않음")
        void badRequest() throws Exception {
            UserSignInRequest userSignInRequest = new UserSignInRequest(
                "test@email.com",
                "test1234@"
            );
            mockMvc.perform(MockMvcRequestBuilders
                    .post(BASE_PATH + "/signin")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(userSignInRequest))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
        }
    }
}
