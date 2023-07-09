package com.picketing.www.persistence.table;

import java.time.LocalDateTime;

public record UserPersist(
    String email,
    String password,
    String name,
    String phoneNumber,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt
) { }
