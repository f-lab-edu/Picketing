package com.picketing.www.presentation.controller.user;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picketing.www.application.filter.encoding.password.PasswordEncoder;
import com.picketing.www.persistence.repository.UserRepository;
import com.picketing.www.persistence.table.UserPersist;
import com.picketing.www.presentation.dto.request.user.UserSignInRequest;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

	private static final String BASE_PATH = "/api/users";

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	PasswordEncoder passwordEncoder;
	@MockBean
	UserRepository userRepository;

	@Nested
	@DisplayName("User 단일 조회")
	public class GetUser {

		@Test
		@Disabled
		@DisplayName("200:존재하는 데이터 정상 조회")
		void success() throws Exception {
			Mockito.when(userRepository.findById(anyLong()))
				.thenReturn(UserPersist.builder()
					.id(1L)
					.email("test@email.com")
					.password("1234567890")
					.name("testUser")
					.phoneNumber("01012345678")
					.createdAt(LocalDateTime.now())
					.modifiedAt(LocalDateTime.now())
					.build()
				);
			mockMvc.perform(MockMvcRequestBuilders
					.get(BASE_PATH + "/1")
					.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@Disabled
		@DisplayName("404:존재하지 않는 데이터")
		void failedBecauseNotFound() throws Exception {
			Mockito.when(userRepository.findById(anyLong()))
				.thenReturn(null);
			mockMvc.perform(MockMvcRequestBuilders
					.get("/api/users/99999")
					.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
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
				.andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@DisplayName("400:이메일 중복")
		void failedBecauseDuplicateEmail() throws Exception {
			Mockito.when(userRepository.existByEmail(anyString()))
				.thenReturn(true);
			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
				"test@email.com", "password1234@"
			);

			mockMvc.perform(MockMvcRequestBuilders
					.post(BASE_PATH)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(userSignUpRequest))
				)
				.andDo(print())
				.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("400:안전하지 않은 비밀번호")
		void failedBecauseNotSafetyPassword() throws Exception {
			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
				"test@email.com", "password123"
			);

			mockMvc.perform(MockMvcRequestBuilders
					.post(BASE_PATH)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(userSignUpRequest))
				)
				.andDo(print())
				.andExpect(status().isBadRequest());
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
				.andDo(print())
				.andExpect(status().isBadRequest());
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
				.andDo(print())
				.andExpect(status().isBadRequest());
		}
	}

	@Nested
	@DisplayName("User 로그인")
	public class SignInUser {

		@Test
		@DisplayName("200:로그인 성공")
		void success() throws Exception {
			Mockito.when(passwordEncoder.encode(anyString()))
				.thenReturn("1234567890");
			Mockito.when(userRepository.findByEmail(Mockito.anyString()))
				.thenReturn(
					Optional.of(UserPersist.builder()
						.id(1L)
						.email("test@email.com")
						.password("1234567890")
						.name("testUser")
						.phoneNumber("01012345678")
						.createdAt(LocalDateTime.now())
						.modifiedAt(LocalDateTime.now())
						.build()
					)
				);
			UserSignInRequest userSignInRequest = new UserSignInRequest(
				"test@email.com",
				"testABC1@"
			);
			mockMvc.perform(MockMvcRequestBuilders
					.post(BASE_PATH + "/signin")
					.accept(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsBytes(userSignInRequest))
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@DisplayName("400:비밀번호가 일치하지 않음")
		void badRequest() throws Exception {
			Mockito.when(passwordEncoder.encode("test1234@"))
				.thenReturn("not_equals_text");
			Mockito.when(userRepository.findByEmail(Mockito.anyString()))
				.thenReturn(
					Optional.of(UserPersist.builder()
						.id(1L)
						.email("test@email.com")
						.password("1234567890")
						.name("testUser")
						.phoneNumber("01012345678")
						.createdAt(LocalDateTime.now())
						.modifiedAt(LocalDateTime.now())
						.build()
					)
				);
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
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(10004));
		}
	}
}
