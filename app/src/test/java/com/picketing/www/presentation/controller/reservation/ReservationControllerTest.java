// package com.picketing.www.presentation.controller.reservation;
//
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.mock.web.MockHttpSession;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.picketing.www.application.exception.ErrorCode;
// import com.picketing.www.business.domain.UserFactory;
// import com.picketing.www.business.domain.reservation.ReservationFactory;
// import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
// import com.picketing.www.business.domain.show.Show;
// import com.picketing.www.business.domain.show.ShowFactory;
// import com.picketing.www.business.domain.show.seat.SeatGrade;
// import com.picketing.www.business.service.reservation.ReservationService;
// import com.picketing.www.business.service.show.ShowService;
// import com.picketing.www.business.type.AgeGroup;
// import com.picketing.www.business.type.Genre;
// import com.picketing.www.business.type.SubGenre;
// import com.picketing.www.persistence.repository.UserRepository;
// import com.picketing.www.persistence.repository.reservation.ScheduledShowSeatRepository;
// import com.picketing.www.persistence.repository.show.ShowRepository;
// import com.picketing.www.presentation.dto.request.reservation.ReservationRequest;
// import com.picketing.www.presentation.dto.request.seat.SaveShowRequest;
// import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
//
// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// @AutoConfigureMockMvc
// class ReservationControllerTest {
//
// 	@Autowired
// 	MockMvc mockMvc;
//
// 	@Autowired
// 	ObjectMapper objectMapper;
//
// 	@Autowired
// 	ReservationService reservationService;
//
// 	@Autowired
// 	ShowService showService;
//
// 	@Autowired
// 	ReservationFactory reservationFactory;
//
// 	@Autowired
// 	ShowRepository showRepository;
//
// 	@Autowired
// 	ShowFactory showFactory;
//
// 	@Autowired
// 	UserRepository userRepository;
//
// 	@Autowired
// 	UserFactory userFactory;
//
// 	@Autowired
// 	ScheduledShowSeatRepository scheduledShowSeatRepository;
//
// 	protected MockHttpSession session;
//
// 	@Nested
// 	@DisplayName("예약 생성")
// 	class MakeReservation {
//
// 		@BeforeEach
// 		public void setSession() throws Exception {
// 			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
// 				"test@email.com", "password1234@"
// 			);
// 			userRepository.save(userFactory.create(userSignUpRequest));
// 			session = new MockHttpSession();
// 			session.setAttribute("login_user", 1L);
// 			// given
// 			SaveShowRequest saveShowRequest = new SaveShowRequest(
// 				"찰리푸스 내한 공연",
// 				Genre.CONCERT,
// 				SubGenre.FOREIGN,
// 				LocalDate.of(2023, 10, 20),
// 				LocalDate.of(2023, 10, 22),
// 				"KSPO DOME",
// 				110L,
// 				0L,
// 				AgeGroup.FOURTEEN,
// 				"찰리푸스 내한 공연 - 서울",
// 				true,
// 				1L
// 			);
//
// 			Show show = showRepository.save(showFactory.createShow(saveShowRequest));
// 			ScheduledShowSeat scheduledShowSeat = new ScheduledShowSeat(show, LocalDateTime.of(2023, 10, 20, 18, 00),
// 				SeatGrade.VIP);
//
// 			scheduledShowSeatRepository.save(scheduledShowSeat);
//
// 		}
//
// 		@Test
// 		@DisplayName("정상적으로 예약 (예매) 생성")
// 		void success() throws Exception {
// 			// when
// 			ReservationRequest reservationRequest = new ReservationRequest(
// 				1L, 1L,
// 				LocalDateTime.of(2023, 10, 20, 18, 00),
// 				List.of(new ReservationRequest.ReservationSeatRequest(
// 					SeatGrade.VIP, 5
// 				))
// 			);
//
// 			// then
// 			mockMvc.perform(MockMvcRequestBuilders
// 					.post("/api/reservation")
// 					.session(session)
// 					.accept(MediaType.APPLICATION_JSON)
// 					.content(objectMapper.writeValueAsBytes(reservationRequest))
// 					.contentType(MediaType.APPLICATION_JSON)
// 				)
// 				.andDo(print())
// 				.andExpect(status().isOk());
//
// 		}
//
// 		@Test
// 		@DisplayName("예매 요청 수량이 잔여 좌석보다 많은 경우, 오류 발생하는지 테스트")
// 		void should_not_make_a_reservation_when_count_is_negative() throws Exception {
// 			ReservationRequest reservationRequest = new ReservationRequest(
// 				1L, 1L,
// 				LocalDateTime.of(2023, 10, 20, 18, 00),
// 				List.of(new ReservationRequest.ReservationSeatRequest(
// 					SeatGrade.VIP, 500
// 				))
// 			);
//
// 			// then
// 			mockMvc.perform(MockMvcRequestBuilders
// 					.post("/api/reservation")
// 					.session(session)
// 					.accept(MediaType.APPLICATION_JSON)
// 					.content(objectMapper.writeValueAsBytes(reservationRequest))
// 					.contentType(MediaType.APPLICATION_JSON)
// 				)
// 				.andDo(print())
// 				.andExpect(status().is4xxClientError())
// 				.andExpect(jsonPath("$.code").value(ErrorCode.ALREADY_RESERVED.getCode()))
// 				.andExpect(jsonPath("$.message").value(ErrorCode.ALREADY_RESERVED.getMessage()));
// 		}
// 	}
//
// }
