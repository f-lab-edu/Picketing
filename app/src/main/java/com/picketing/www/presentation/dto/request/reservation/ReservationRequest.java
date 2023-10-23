package com.picketing.www.presentation.dto.request.reservation;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime showTime,

	@JsonProperty(value = "reservationSeatRequests")
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
