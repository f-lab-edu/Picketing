package com.picketing.www.presentation.controller.schedule;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.picketing.www.persistence.repository.schedule.ScheduleRepository;
import com.picketing.www.persistence.table.schedule.DateSchedulePersist;
import com.picketing.www.persistence.table.schedule.DateScheduleView;
import com.picketing.www.persistence.table.schedule.TimeSchedulePersist;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ScheduleControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Nested
	@DisplayName("Schedule 조회")
	class GetSchedules {

		@Test
		@DisplayName("정상 조회")
		void success() throws Exception {
			List<DateScheduleView> testSchedule = new ArrayList<>();
			testSchedule.add(new DateScheduleView(
				new DateSchedulePersist(
					1L, 1L, "One day show", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 01)
				),
				List.of(
					new TimeSchedulePersist(1L, 1L, LocalTime.of(05, 00), LocalTime.of(07, 30))
				)
			));
			testSchedule.add(new DateScheduleView(
				new DateSchedulePersist(
					2L, 1L, "One day show", LocalDate.of(2023, 01, 02), LocalDate.of(2023, 01, 02)
				),
				List.of(
					new TimeSchedulePersist(2L, 2L, LocalTime.of(05, 00), LocalTime.of(07, 30))
				)
			));
			scheduleRepository.save(testSchedule);

			MockHttpSession session = new MockHttpSession();
			session.setAttribute("login_user", "test@email.com");
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows/1/schedules")
						.session(session)
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("yearMonth", "2023-01")
				).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.schedules.length()").value(2))
				.andExpect(jsonPath("$.schedules[0].timeSchedules.length()").value(1));
		}
	}
}
