package com.picketing.www.presentation.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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

        @Test
        @DisplayName("200:로그인 성공")
        void success() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post(BASE_PATH + "/signin")
                            .accept(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk());
        }


    }
}