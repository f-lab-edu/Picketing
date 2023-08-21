package com.picketing.www.presentation.controller.show;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picketing.www.business.domain.Show;
import com.picketing.www.business.domain.ShowFactory;
import com.picketing.www.business.service.ShowService;
import com.picketing.www.presentation.dto.response.show.ShowMainResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/shows")
@RestController
public class ShowController {

	private final ShowFactory showFactory;

	private final ShowService showService;
	
	@GetMapping
	public List<ShowMainResponse> getShowListWithPagination(
		@RequestParam(value = "genre", required = false, defaultValue = "ALL") String genre,
		@RequestParam(value = "subGenre", required = false, defaultValue = "ALL") String subGenre,
		@PageableDefault(size = 10, page = 0, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable) {
		System.out.println("genre = " + genre);
		System.out.println("subGenre = " + subGenre);
		System.out.println("pageable = " + pageable);
		List<Show> showList = showService.getShowList(pageable);
		return showList.stream()
			.map(showFactory::findResponse)
			.collect(Collectors.toList());
	}

	// @GetMapping("/{showId}")
	// public Page<ShowMainResponse> getShowDetails(@PathVariable Long showId) {
	// 	return showFactory.showMainResponse(
	//
	// 	);
	// }
}
