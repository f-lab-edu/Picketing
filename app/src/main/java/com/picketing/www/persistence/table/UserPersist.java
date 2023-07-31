package com.picketing.www.persistence.table;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserPersist(
	String email,
	String password,
	String name,
	String phoneNumber,
	LocalDateTime createdAt,
	LocalDateTime modifiedAt
) {
}
