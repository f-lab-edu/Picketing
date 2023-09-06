package com.picketing.www.persistence.repository.show;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.picketing.www.persistence.table.SeatGradePersist;
import com.picketing.www.persistence.table.SeatPersist;

@Configuration
public class ShowStoreConfiguration {

	@Profile({"!test"})
	@Bean
	public Map<Long, SeatGradePersist> seatGradeStore() {
		return new ConcurrentHashMap<>();
	}

	@Profile({"!test"})
	@Bean
	public Map<Long, SeatPersist> seatStore() {
		return new ConcurrentHashMap<>();
	}
}
