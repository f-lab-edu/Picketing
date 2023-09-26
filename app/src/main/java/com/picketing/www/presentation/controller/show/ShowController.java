package com.picketing.www.presentation.controller.show;

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

import com.picketing.www.business.domain.show.Show;
import com.picketing.www.business.domain.show.ShowFactory;
import com.picketing.www.business.domain.show.seat.SeatGradeFactory;
import com.picketing.www.business.service.show.ShowService;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.presentation.dto.response.show.ShowMainResponse;
import com.picketing.www.presentation.dto.response.show.ShowSeatGradeResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/shows")
@RestController
public class ShowController {

	private final ShowFactory showFactory;

	private final ShowService showService;

	private final SeatGradeFactory seatGradeFactory;

	@GetMapping
	public Page<ShowMainResponse> getShowListWithPagination(
		@RequestParam(value = "genre", required = false, defaultValue = "CONCERT") Genre genre,
		@RequestParam(value = "subGenre", required = false, defaultValue = "FESTIVAL") SubGenre subGenre,
		@PageableDefault(size = 10, page = 0, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {
		List<Show> showList = showService.getShowList(genre, subGenre, pageable);
		List<ShowMainResponse> response = showList.stream()
			.map(showFactory::findResponse)
			.collect(Collectors.toList());
		return new PageImpl<>(response, pageable, response.size());
	}

	@GetMapping("/{showId}/seatGrade")
	public ShowSeatGradeResponse getShowSeatGradeList(
		@PathVariable Long showId
	) {
		return seatGradeFactory.convertSeatGradeToResponse(
			showService.getShowSeatGradeList(showId)
		);
	}

}
