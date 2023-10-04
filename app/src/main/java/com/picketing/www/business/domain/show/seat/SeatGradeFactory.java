// package com.picketing.www.business.domain.show.seat;
//
// import static com.picketing.www.presentation.dto.response.show.ShowSeatGradeResponse.*;
//
// import java.util.List;
// import java.util.stream.Collectors;
//
// import org.springframework.stereotype.Component;
//
// import com.picketing.www.presentation.dto.request.seat.SaveSeatGradeRequest;
// import com.picketing.www.presentation.dto.response.show.ShowSeatGradeResponse;
//
// @Component
// public class SeatGradeFactory {
//
// 	public ShowSeatGradeResponse convertSeatGradeToResponse(List<SeatGrade> seatGrade) {
// 		return new ShowSeatGradeResponse(
// 			seatGrade.stream()
// 				.map(this::convertSeatGradeToDto)
// 				.collect(Collectors.toList())
// 		);
// 	}
//
// 	public ShowSeatGradeResponseDto convertSeatGradeToDto(SeatGrade seatGrade) {
// 		return ShowSeatGradeResponseDto.builder()
// 			.showId(seatGrade.getSeat().getShow().getId())
// 			.seatGradeName(seatGrade.getName())
// 			.price(seatGrade.getPrice())
// 			.build();
// 	}
//
// 	public SeatGrade createSeatGrade(SaveSeatGradeRequest saveSeatGradeRequest) {
// 		return SeatGrade.builder()
// 			.name(saveSeatGradeRequest.name())
// 			.seat(saveSeatGradeRequest.seat())
// 			.price(saveSeatGradeRequest.price())
// 			.build();
// 	}
// }

package com.picketing.www.business.domain.show.seat;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.response.show.seat.ScheduledShowSeatGradeResponse;
import com.picketing.www.presentation.dto.response.show.seat.SeatGradeResponse;

@Component
public class SeatGradeFactory {

	public SeatGradeResponse convertSeatGradeToResponse(SeatGrade seatGrade) {
		return SeatGradeResponse.builder()
			.seatGrade(seatGrade.getName())
			.totalCount(seatGrade.getCount())
			.build();
	}

	public SeatGradeResponse convertSeatGradeFromShowSeat(ScheduledShowSeatGradeResponse scheduledShowSeat) {
		return SeatGradeResponse.builder()
			.seatGrade(scheduledShowSeat.seatGrade().getName())
			.totalCount(scheduledShowSeat.seatGrade().getCount())
			.build();
	}
}
