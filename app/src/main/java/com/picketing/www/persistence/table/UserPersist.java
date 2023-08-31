package com.picketing.www.persistence.table;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserPersist {
	Long id;
	String email;
	String password;
	String name;
	String phoneNumber;
	LocalDateTime createdAt;
	LocalDateTime modifiedAt;

	// FIXME JPA 전환 완료 시, 아래 함수 삭제 및 UserPersist 다시 record로 변경
	public UserPersist createUserPersist(Long id) {
		this.id = id;
		return this;
	}
}
