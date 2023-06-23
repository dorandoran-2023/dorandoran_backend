package com.dorandoran.dorandoran.core.user.domain;

import static com.dorandoran.dorandoran.global.util.Preconditions.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dorandoran.dorandoran.core.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(updatable = false)
	private Long id;

	@Embedded
	@Column(name = "이메일")
	private Email email;

	@Embedded
	@Column(name = "패스워드")
	private Password password;

	@Enumerated
	@Column(name = "회원타입")
	private UserType userType;

	public User(Email email, Password password, UserType userType) {
		checkNotNull(email, "User.email cannot be null");
		checkNotNull(password, "User.password cannot be null");
		checkNotNull(userType, "User.userType cannot be null");
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
}
