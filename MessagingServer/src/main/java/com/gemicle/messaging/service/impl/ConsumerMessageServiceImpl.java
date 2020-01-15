package com.gemicle.messaging.service.impl;

import java.io.IOException;
import java.util.List;

import com.gemicle.messaging.entity.NotificationMessage;
import com.gemicle.messaging.entity.SubscriptionMessage;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemicle.messagereceivers.rabbit.connection.producers.CommunicationProducer;
import com.gemicle.messaging.service.ConsumerMessageService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Slf4j
@Service
public class ConsumerMessageServiceImpl implements ConsumerMessageService {

	@Resource(name = "communicationProducer")
	private CommunicationProducer communicationProducer;

	@Resource(name = "objectMapper")
	private ObjectMapper objectMapper;

	@Resource(name = "notificationsQueueKey")
	private String notificationsQueueKey;

	@Resource(name = "subscriptionsQueueKey")
	private String subscriptionsQueueKey;

	@Override
	public void sendMessages(List<NotificationMessage> notifications) {
		try {
			byte[] message = objectMapper.writeValueAsBytes(notifications);
			communicationProducer.sendMessage(message, notificationsQueueKey);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void sendSubscription(String deviceToken, boolean subscribeOn, List<String> topicList) {
		SubscriptionMessage subscription = new SubscriptionMessage(deviceToken, subscribeOn,topicList);
		try {
			byte[] message = objectMapper.writeValueAsBytes(subscription);
			communicationProducer.sendMessage(message, subscriptionsQueueKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
