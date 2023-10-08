package com.picketing.www.presentation.dto.request.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.picketing.www.business.domain.show.seat.SeatGrade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ReservationRequest(
	@Positive
	Long showId,
	@Positive
	Long userId,

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime showTime,
	List<ReservationSeatRequest> seatGradeList
) {

	@Builder
	public record ReservationSeatRequest(
		@NotBlank
		SeatGrade seatGrade,
		@Min(value = 1, message = "구매할 개수는 1이상의 값이어야 합니다.")
		int count
	) {
	}
}
