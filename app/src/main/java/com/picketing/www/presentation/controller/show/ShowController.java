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
import com.picketing.www.business.service.show.ShowService;
import com.picketing.www.business.type.Genre;
import com.picketing.www.business.type.SubGenre;
import com.picketing.www.presentation.dto.response.show.ShowMainResponse;
import com.picketing.www.presentation.dto.response.show.ShowSeatPriceResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/shows")
@RestController
public class ShowController {

	private final ShowFactory showFactory;

	private final ShowService showService;

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

	@GetMapping("/{showId}/basic-price")
	public ShowSeatPriceResponse getShowSeatGradePriceList(
		@PathVariable Long showId
	) {
		List<ShowSeatPriceResponse> seatBasicPriceList = showService.getShowSeatGradePriceList(showId)
			.stream()
			.map(s -> ShowSeatPriceResponse.ShowSeatBasicPriceResponseDto.builder()
				.showId(s)
				.build())
			.collect(Collectors.toList());

		return ShowSeatPriceResponse.builder().build();
	}

}
