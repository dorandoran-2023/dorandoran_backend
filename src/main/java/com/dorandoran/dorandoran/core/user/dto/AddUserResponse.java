package com.dorandoran.dorandoran.core.user.dto;

import com.dorandoran.dorandoran.core.user.domain.User;
import com.dorandoran.dorandoran.core.user.domain.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {
	private String email;
	private UserType userType;

	public static AddUserResponse ofEntity(User user) {
		return new AddUserResponse(user.getEmail().getValue(), user.getUserType());
	}
}
