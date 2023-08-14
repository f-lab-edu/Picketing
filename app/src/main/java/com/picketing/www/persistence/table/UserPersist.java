package com.picketing.www.persistence.table;

import java.time.LocalDateTime;

import lombok.Builder;

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
