package com.dorandoran.dorandoran.infra.nurigo;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

import com.dorandoran.dorandoran.config.nurigo.NurigoProperties;
import com.dorandoran.dorandoran.core.user.application.SmsService;

public class NurigoSmsService implements SmsService {
	private final DefaultMessageService nurigoMessageService;
	private final String from;

	public NurigoSmsService(NurigoProperties properties) {
		this.nurigoMessageService = NurigoApp.INSTANCE.initialize(
			properties.getApiKey(),
			properties.getApiSecret(),
			properties.getDomain()
		);
		this.from = properties.getFromNumber();
	}

	@Override
	public void sendAuthenticationCode(String to, String code) {
		Message message = new Message();
		message.setFrom(from);
		message.setTo(to);
		message.setText("인증코드 " + code + " 를 입력하세요.");

		try {
			nurigoMessageService.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
