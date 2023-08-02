package com.picketing.www.business.type;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Genre {

    CONCERT("콘서트", Arrays.asList(SubGenre.DOMESTIC, SubGenre.FOREIGN, SubGenre.FESTIVAL)),
    MUSICAL("뮤지컬", Arrays.asList(SubGenre.ORIGINAL, SubGenre.LICENSE, SubGenre.CREATIVE, SubGenre.NONVERBAL)),
    PLAY("연극", Arrays.asList(SubGenre.PLAY)),
    SPORT("스포츠", Arrays.asList(SubGenre.BASEBALL, SubGenre.FOOTBALL, SubGenre.ESPORTS));

    private final String genre;

    private final List<SubGenre> subGenre;

    Genre(String genre, List<SubGenre> subGenre) {
        this.genre = genre;
        this.subGenre = subGenre;
    }
}
