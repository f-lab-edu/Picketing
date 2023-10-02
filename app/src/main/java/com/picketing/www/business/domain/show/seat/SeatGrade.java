package com.picketing.www.business.domain.show.seat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatGrade {

	VIP("VIP", 165000L, 2000),
	SR("SR", 154000L, 1000),
	R("R", 143000L, 700),
	S("S", 121000L, 800),
	A("A", 100000L, 300);

	private final String name; // 공연장의 좌석 명칭

	private final Long price; // 공연장의 좌석 가격 (원래는 공연마다 가격이 달라지므로, 이 엔티티 필드가 아니지만, 현재는 공연 좌석이 고정되어 있으므로 price를 공연 좌석에 둡니다)

	private final int count; // 공연장의 좌석 개수
}
