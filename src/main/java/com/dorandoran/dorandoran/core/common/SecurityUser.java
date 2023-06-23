package com.dorandoran.dorandoran.core.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecurityUser {
	private final long userId;
	private final long profileId;
}
