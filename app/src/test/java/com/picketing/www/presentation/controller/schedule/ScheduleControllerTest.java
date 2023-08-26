package com.picketing.www.presentation.controller.schedule;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.picketing.www.persistence.repository.schedule.ScheduleRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ScheduleControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ScheduleRepository scheduleRepository;

	@Nested
	@DisplayName("Schedule 조회")
	class GetSchedules {

		@Test
		@Disabled
		@DisplayName("정상 조회")
		void success() throws Exception {
			mockMvc.perform(
					MockMvcRequestBuilders
						.get("/api/shows/1/schedules")
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("yearAndMonth", "2023.08")
				).andDo(print())
				.andExpect(status().isOk());
		}
	}
}