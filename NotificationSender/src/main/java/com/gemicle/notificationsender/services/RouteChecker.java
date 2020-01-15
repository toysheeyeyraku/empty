package com.gemicle.notificationsender.services;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

import com.gemicle.mailingsettings.enums.MailingType;
import com.gemicle.notificationsender.dto.MessageToSendDTO;

public class RouteChecker implements MessageSelector {
	private MailingType type;
	@Override
	public boolean accept(Message<?> message) {
		MessageToSendDTO data=(MessageToSendDTO) message.getPayload();
		if (data.getEnabledMailingTypes().equals(type)) {
			return true;
		}
		return false;
	}
	public RouteChecker(MailingType type) {
		this.type=type;
	}

}
