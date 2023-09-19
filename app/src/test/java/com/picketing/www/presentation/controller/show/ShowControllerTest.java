package com.picketing.www.presentation.controller.show;

import static com.picketing.www.business.domain.show.Show.*;
import static com.picketing.www.business.domain.show.seatgrade.SeatGrade.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.seatgrade.SeatGrade;
import com.picketing.www.business.type.AgeGroup;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.persistence.repository.show.ShowRepository;
import com.picketing.www.persistence.repository.show.seatgrade.SeatGradeRepository;

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
	@DisplayName("공연의 좌석 가격 리스트 조회")
	class GetShowSeatGradeList {

		@Test
		@DisplayName("특정 공연의 좌석 가격 리스트 조회")
		void has_seat_grade_price_list_when_search_show() throws

			Exception {
			Show latestShow = createShow(1L, "임영웅 콘서트 IM HERO TOUR 2023 - 서울", Genre.CONCERT, SubGenre.DOMESTIC,
				LocalDate.of(2023, 10, 27), LocalDate.of(2023, 11, 5), "KSPO DOME(올림픽체조경기장)", 150L, 0L, AgeGroup.SEVEN,
				"임영웅 콘서트 투어", true, 1L);
			showRepository.save(latestShow);

			List<SeatGrade> seatGradeList = new ArrayList<>();
			seatGradeList.add(createSeatGrade(
				1L, "VIP", latestShow, new BigDecimal(165000)
			));
			seatGradeList.add(createSeatGrade(
				1L, "SR", latestShow, new BigDecimal(154000)
			));
			seatGradeList.add(createSeatGrade(
				1L, "R", latestShow, new BigDecimal(143000)
			));
			seatGradeList.add(createSeatGrade(
				1L, "S", latestShow, new BigDecimal(121000)
			));

			seatGradeRepository.saveAll(seatGradeList);

			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", 1L);
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows/1/basic-price")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
				).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.basicPriceList.length()").value(4))
			;
		}
	}
}
