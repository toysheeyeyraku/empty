package com.gemicle.messaging;

import org.apache.commons.collections.map.HashedMap;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.gemicle.events.PrepareSendNotificationEvent;
import com.gemicle.kovtun.eventing.annotations.EnableEventingProducer;
import com.gemicle.kovtun.eventing.annotations.EnableEventingProducerAutoConfigure;
import com.gemicle.kovtun.eventing.config.EventingProducerAutoConfigure.IventSendler;
import com.gemicle.messaging.entity.NotificationData;
import com.gemicle.messaging.entity.NotificationMessage;
import com.gemicle.messaging.enums.MessageType;
import com.gemicle.messaging.enums.NewsType;
import com.gemicle.messaging.enums.NotificationType;

import io.swagger.annotations.Authorization;
@EnableEventingProducer
@EnableEventingProducerAutoConfigure
@SpringBootApplication(scanBasePackages = {"com.gemicle.kovtun","com.gemicle.messaging"})

public class MessagingApplication implements CommandLineRunner{
	
	
    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		
	}
}
