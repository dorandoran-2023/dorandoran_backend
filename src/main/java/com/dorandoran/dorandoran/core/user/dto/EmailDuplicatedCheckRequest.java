package com.dorandoran.dorandoran.core.user.dto;

import com.dorandoran.dorandoran.core.user.domain.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDuplicatedCheckRequest {
	private Email email;
}
