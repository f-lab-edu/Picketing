package com.picketing.www.business.type;

import lombok.Getter;

@Getter
public enum SubGenre {

    DOMESTIC("국내뮤지션"),
    FOREIGN("해외뮤지션"),
    FESTIVAL("페스티벌"),

    ORIGINAL("오리지널"),
    LICENSE("라이센스"),
    CREATIVE("창작"),
    NONVERBAL("넌버벌"),

    PLAY("연극전체"),

    BASEBALL("야구"),
    FOOTBALL("축구"),
    ESPORTS("E스포츠");

    private final String subGenre;

    SubGenre(String subGenre) {
        this.subGenre = subGenre;
    }
}
