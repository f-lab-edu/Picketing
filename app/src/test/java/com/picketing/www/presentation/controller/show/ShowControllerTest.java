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

import com.picketing.www.business.domain.show.ShowFactory;
import com.picketing.www.business.domain.show.seat.SeatGradeFactory;
import com.picketing.www.persistence.repository.show.ShowRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ShowControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ShowRepository showRepository;

	@Autowired
	SeatGradeRepository seatGradeRepository;

	@Autowired
	ShowFactory showFactory;

	@Autowired
	SeatGradeFactory seatGradeFactory;

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

	// @Disabled
	// @Nested
	// @DisplayName("공연의 좌석 가격 리스트 조회")
	// class GetShowSeatGradeList {
	//
	// 	// 	@Test
	// 	// 	@DisplayName("특정 공연의 좌석 가격 리스트 조회")
	// 	// 	void has_seat_grade_price_list_when_search_show() throws
	// 	// 		Exception {
	// 	//
	// 	// 		Show latestShow = showFactory.createShow(SaveShowRequest.builder()
	// 	// 			.title("임영웅 콘서트 IM HERO TOUR 2023 - 서울")
	// 	// 			.genre(Genre.CONCERT)
	// 	// 			.subGenre(SubGenre.DOMESTIC)
	// 	// 			.startDate(LocalDate.of(2023, 10, 27))
	// 	// 			.endDate(LocalDate.of(2023, 11, 5))
	// 	// 			.venue("KSPO DOME(올림픽체조경기장)")
	// 	// 			.runningTime(150L)
	// 	// 			.intermission(0L)
	// 	// 			.ageGroup(AgeGroup.SEVEN)
	// 	// 			.details("임영웅 전국 콘서트 투어")
	// 	// 			.isBookable(true)
	// 	// 			.ownerId(1L)
	// 	// 			.build()
	// 	// 		);
	// 	// 		showRepository.save(latestShow);
	// 	//
	// 	// 		List<Seat> seatList = new ArrayList<>(10);
	// 	// 		for (int i = 0; i < 50; i++) {
	// 	// 			seatList.add(seatFactory.createSeat(
	// 	// 				SaveSeatRequest.builder()
	// 	// 					.show(latestShow)
	// 	// 					.timeScheduleId(1L)
	// 	// 					.build())
	// 	// 			);
	// 	// 		}
	// 	// 		seatRepository.saveAll(seatList);
	// 	//
	// 	// 		List<SeatGrade> seatGradeList = new ArrayList<>(10);
	// 	// 		for (int i = 0; i < 15; i++) {
	// 	// 			seatGradeList.add(seatGradeFactory.createSeatGrade(
	// 	// 				SaveSeatGradeRequest.builder()
	// 	// 					.name("VIP")
	// 	// 					.seat(seatList.get(i))
	// 	// 					.price(new BigDecimal(165000))
	// 	// 					.build()
	// 	// 			));
	// 	// 		}
	// 	//
	// 	// 		for (int i = 15; i < 25; i++) {
	// 	// 			seatGradeList.add(seatGradeFactory.createSeatGrade(
	// 	// 				SaveSeatGradeRequest.builder()
	// 	// 					.name("SR")
	// 	// 					.seat(seatList.get(i))
	// 	// 					.price(new BigDecimal(154000))
	// 	// 					.build()
	// 	// 			));
	// 	// 		}
	// 	//
	// 	// 		for (int i = 25; i < 37; i++) {
	// 	// 			seatGradeList.add(seatGradeFactory.createSeatGrade(
	// 	// 				SaveSeatGradeRequest.builder()
	// 	// 					.name("R")
	// 	// 					.seat(seatList.get(i))
	// 	// 					.price(new BigDecimal(143000))
	// 	// 					.build()
	// 	// 			));
	// 	// 		}
	// 	//
	// 	// 		for (int i = 37; i < 50; i++) {
	// 	// 			seatGradeList.add(seatGradeFactory.createSeatGrade(
	// 	// 				SaveSeatGradeRequest.builder()
	// 	// 					.name("S")
	// 	// 					.seat(seatList.get(i))
	// 	// 					.price(new BigDecimal(121000))
	// 	// 					.build()
	// 	// 			));
	// 	// 		}
	// 	// 		seatGradeRepository.saveAll(seatGradeList);
	// 	//
	// 	// 		MockHttpSession session = new MockHttpSession();
	// 	// 		session.setAttribute("login_user", 1L);
	// 	// 		mockMvc.perform(
	// 	// 				MockMvcRequestBuilders
	// 	// 					.get("/api/shows/1/seatGrade")
	// 	// 					.session(session)
	// 	// 					.accept(MediaType.APPLICATION_JSON)
	// 	// 			).andDo(print())
	// 	// 			.andExpect(status().isOk())
	// 	// 			.andExpect(jsonPath("$.seatGradeList.length()").value(50));
	// 	// 	}
	// }
}
