package com.picketing.www.presentation.controller.show;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ShowControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Nested
	@DisplayName("Show 조회")
	class GetShowListWithPagination {

		@Test
		@DisplayName("아무 쿼리도 조회하지 않는 경우 정상조회")
		void noSearchQuery() throws Exception {
			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
				).andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@DisplayName("공백으로 조회 할 경우 정상조회")
		void hasEmptySearchQuery() throws Exception {
			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("genre", "")
						.queryParam("subGenre", "")
				).andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@DisplayName("Genre 쿼리 조회하는 경우 정상조회")
		void hasGenreSearchQuery() throws Exception {
			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("genre", "CONCERT")
				).andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@DisplayName("SubGenre 쿼리 조회하는 경우 정상조회")
		void hasSubGenreSearchQuery() throws Exception {
			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("subGenre", "ORIGINAL")
				).andDo(print())
				.andExpect(status().isOk());
		}

		@Test
		@DisplayName("Genre, SubGenre 쿼리 조회하는 경우 정상조회")
		void hasGenreAndSubGenreSearchQuery() throws Exception {
			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("genre", "MUSICAL")
						.queryParam("subGenre", "ORIGINAL")
				).andDo(print())
				.andExpect(status().isOk());
		}
	}

	@Nested
	@DisplayName("SeatGrade 남은 좌석 조회")
	class GetRemainingSeatCountsInShow {

		@Test
		@DisplayName("데이터가 존재하지 않는 경우 정상 조회")
		void noDataButOk() throws Exception {
			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);

			Integer showId = 1;
			Integer timeScheduleId = 1;
			String url = String.format(
				"/api/shows/%s/schedule/times/%s/seats/grades/remaining-counts",
				showId, timeScheduleId
			);

			System.out.println("url: " + url);

			mockMvc.perform(
					MockMvcRequestBuilders
						.get(url)
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
				).andDo(print())
				.andExpect(status().isOk());
		}
	}
}
