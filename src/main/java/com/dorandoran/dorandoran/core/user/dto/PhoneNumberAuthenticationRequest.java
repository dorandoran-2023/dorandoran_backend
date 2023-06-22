package com.dorandoran.dorandoran.core.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberAuthenticationRequest {
	private String phoneNumber;
	private String code;
}
