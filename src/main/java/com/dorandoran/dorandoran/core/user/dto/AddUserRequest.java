package com.dorandoran.dorandoran.core.user.dto;

import com.dorandoran.dorandoran.core.user.domain.Email;
import com.dorandoran.dorandoran.core.user.domain.Password;
import com.dorandoran.dorandoran.core.user.domain.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
	private Email email;
	private Password password;
	private Password checkingPassword;
	private UserType userType;
}
