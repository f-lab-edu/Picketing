package com.picketing.www.business.domain.show.seat;

import jakarta.persistence.*;
import lombok.*;


@Getter
@AllArgsConstructor
public enum SeatGradeEnum {

    VIP(50000, "VIP석"),
    R(40000, "R석");

    private long price;
    private String name;

}
