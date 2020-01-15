package com.gemicle.notificationsender.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.gemicle.eventing.kovtun.eventhandler.AutoRegisteredEventHandler;
import com.gemicle.events.PrepareSendNotificationEvent;
import com.google.common.eventbus.Subscribe;

@Component
public class HandlePrepareSendEvent implements AutoRegisteredEventHandler<PrepareSendNotificationEvent> {
	@Autowired
	@Qualifier("eventChannel")
	private MessageChannel channel;

	@Subscribe
	@Override
	public void process(PrepareSendNotificationEvent data) {
		System.out.println("Get Prepare Send Event");
		channel.send(MessageBuilder.withPayload(data).build());

	}

}
