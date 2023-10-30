// package com.picketing.www.business.service.reservation;
//
// import static com.picketing.www.presentation.dto.request.reservation.ReservationRequest.*;
//
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.concurrent.CountDownLatch;
// import java.util.stream.Collectors;
// import java.util.stream.Stream;
//
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.mock.web.MockHttpSession;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// import com.picketing.www.business.domain.User;
// import com.picketing.www.business.domain.UserFactory;
// import com.picketing.www.business.domain.reservation.ScheduledShowSeat;
// import com.picketing.www.business.domain.show.Show;
// import com.picketing.www.business.domain.show.ShowFactory;
// import com.picketing.www.business.domain.show.seat.SeatGrade;
// import com.picketing.www.business.service.user.UserService;
// import com.picketing.www.business.type.AgeGroup;
// import com.picketing.www.business.type.Genre;
// import com.picketing.www.business.type.SubGenre;
// import com.picketing.www.persistence.repository.UserRepository;
// import com.picketing.www.persistence.repository.reservation.ReservationRepository;
// import com.picketing.www.persistence.repository.reservation.ScheduledShowSeatRepository;
// import com.picketing.www.persistence.repository.show.ShowRepository;
// import com.picketing.www.presentation.dto.request.reservation.ReservationRequest;
// import com.picketing.www.presentation.dto.request.seat.SaveShowRequest;
// import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
//
// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// @AutoConfigureMockMvc
// class ReservationServiceTest {
//
// 	@Autowired
// 	private ReservationService reservationService;
//
// 	@Autowired
// 	private UserService userService;
//
// 	@Autowired
// 	UserRepository userRepository;
//
// 	@Autowired
// 	UserFactory userFactory;
//
// 	@Autowired
// 	ShowRepository showRepository;
//
// 	@Autowired
// 	ShowFactory showFactory;
//
// 	@Autowired
// 	ScheduledShowSeatRepository scheduledShowSeatRepository;
//
// 	@Autowired
// 	ReservationRepository reservationRepository;
//
// 	protected MockHttpSession session;
//
// 	private User savedUser;
//
// 	private Show show;
//
// 	private ReservationSeatRequest seatRequest;
//
// 	private ScheduledShowSeat scheduledShowSeat;
//
// 	@BeforeEach
// 	void make_four_reservations_for_grade_A_seat() throws Exception {
// 		UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
// 			"test@email.com", "password1234@"
// 		);
// 		savedUser = userRepository.save(userFactory.create(userSignUpRequest));
// 		session = new MockHttpSession();
// 		session.setAttribute("login_user", 1L);
//
// 		SaveShowRequest saveShowRequest = new SaveShowRequest(
// 			"찰리푸스 내한 공연",
// 			Genre.CONCERT,
// 			SubGenre.FOREIGN,
// 			LocalDate.of(2023, 10, 20),
// 			LocalDate.of(2023, 10, 22),
// 			"KSPO DOME",
// 			110L,
// 			0L,
// 			AgeGroup.FOURTEEN,
// 			"찰리푸스 내한 공연 - 서울",
// 			true,
// 			1L
// 		);
//
// 		show = showRepository.save(showFactory.createShow(saveShowRequest));
// 		scheduledShowSeat = new ScheduledShowSeat(show, LocalDateTime.of(2023, 10, 20, 18, 00),
// 			SeatGrade.A);
//
// 		scheduledShowSeatRepository.save(scheduledShowSeat);
//
// 		final SeatGrade seatGrade = SeatGrade.A;
// 		final int count = 4;
// 		final LocalDateTime showTime = LocalDateTime.of(2023, 10, 20, 18, 00);
//
// 		seatRequest = ReservationSeatRequest.builder()
// 			.seatGrade(seatGrade)
// 			.count(count)
// 			.build();
//
// 		ReservationRequest reservationRequest = ReservationRequest.builder()
// 			.showId(show.getId())
// 			.userId(savedUser.getId())
// 			.showTime(showTime)
// 			.seatGradeList(List.of(seatRequest))
// 			.build();
//
// 		reservationService.makeReservations(savedUser, show, showTime, List.of(seatRequest));
//
// 	}
//
// 	@Test
// 	@DisplayName("잔여 좌석이 30석인 A석에, 이미 4명이 예매를 완료하였고, 남은 26자리에 30명이 동시에 참여하는 상황 테스트")
// 	void make_reservations_for_ticketing() throws InterruptedException {
// 		final int participants_number = 30;
// 		final int maximum_number = 26;
//
// 		// given
// 		CountDownLatch countDownLatch = new CountDownLatch(participants_number);
//
// 		List<MakingReservationWorker> workers = Stream
// 			.generate(() -> new MakingReservationWorker(savedUser, show, LocalDateTime.of(2023, 10, 20, 18, 00),
// 				countDownLatch, List.of(ReservationSeatRequest.builder()
// 				.seatGrade(SeatGrade.A)
// 				.count(1)
// 				.build()
// 			)
// 			))
// 			.limit(participants_number)
// 			.collect(Collectors.toList());
//
// 		// when
// 		workers.forEach(worker -> new Thread(worker).start());
// 		countDownLatch.await();
//
// 		// then
// 		Long reservedCount = reservationRepository.countReservationsByShowSeat(scheduledShowSeat);
//
// 		Assertions.assertThat(reservedCount).isEqualTo(maximum_number);
// 	}
//
// 	private class MakingReservationWorker implements Runnable {
// 		private User user;
// 		private Show show;
// 		private LocalDateTime showTime;
//
// 		private CountDownLatch countDownLatch;
//
// 		private List<ReservationSeatRequest> reservationSeatRequest;
//
// 		public MakingReservationWorker(User user, Show show, LocalDateTime showTime, CountDownLatch countDownLatch,
// 			List<ReservationSeatRequest> reservationSeatRequest) {
// 			this.user = user;
// 			this.show = show;
// 			this.showTime = showTime;
// 			this.countDownLatch = countDownLatch;
// 			this.reservationSeatRequest = reservationSeatRequest;
// 		}
//
// 		@Override
// 		public void run() {
// 			reservationService.makeReservations(user, show, showTime, reservationSeatRequest);
// 			countDownLatch.countDown();
//
// 		}
// 	}
//
// }
