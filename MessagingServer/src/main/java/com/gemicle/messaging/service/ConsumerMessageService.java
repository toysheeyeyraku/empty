package com.gemicle.messaging.service;

import java.util.List;

import com.gemicle.messaging.entity.NotificationMessage;

public interface ConsumerMessageService {
	void sendMessages(List<NotificationMessage> notifications);
	void sendSubscription(String deviceToken, boolean subscribeOn, List<String> topicList);
}
