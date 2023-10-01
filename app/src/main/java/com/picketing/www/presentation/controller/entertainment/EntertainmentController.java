package com.picketing.www.presentation.controller.entertainment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picketing.www.business.domain.entertainment.Entertainment;
import com.picketing.www.business.domain.entertainment.ShowFactory;
import com.picketing.www.business.domain.entertainment.seat.SeatGradeFactory;
import com.picketing.www.business.service.entertainment.EntertainmentService;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.presentation.dto.response.entertainment.EntertainmentMainResponse;
import com.picketing.www.presentation.dto.response.entertainment.EntertainmentSeatGradeResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/shows")
@RestController
public class EntertainmentController {

	private final ShowFactory showFactory;

	private final EntertainmentService entertainmentService;

	private final SeatGradeFactory seatGradeFactory;

	@GetMapping
	public Page<EntertainmentMainResponse> getShowListWithPagination(
		@RequestParam(value = "genre", required = false, defaultValue = "CONCERT") Genre genre,
		@RequestParam(value = "subGenre", required = false, defaultValue = "FESTIVAL") SubGenre subGenre,
		@PageableDefault(size = 10, page = 0, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {
		List<Entertainment> entertainmentList = entertainmentService.getShowList(genre, subGenre, pageable);
		List<EntertainmentMainResponse> response = entertainmentList.stream()
			.map(showFactory::findResponse)
			.collect(Collectors.toList());
		return new PageImpl<>(response, pageable, response.size());
	}

	@GetMapping("/{showId}/seatGrade")
	public EntertainmentSeatGradeResponse getShowSeatGradeList(
		@PathVariable Long showId
	) {
		return seatGradeFactory.convertSeatGradeToResponse(
			entertainmentService.getShowSeatGradeList(showId)
		);
	}

}
