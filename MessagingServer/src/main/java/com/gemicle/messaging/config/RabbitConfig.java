package com.gemicle.messaging.config;

import com.gemicle.messagereceivers.rabbit.connection.producers.CommunicationProducerImpl;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

@Configuration
@ComponentScan(basePackages = { "com.gemicle.messagereceivers.*" })
public class RabbitConfig {
	@Autowired
	private Environment env;

	@Bean(name = "exchangeName")
	public String exchangeName() {
		return env.getProperty("rabbitMQ.exchange.name");
	}

	@Bean(name = "exchangeType")
	public String exchangeType() {
		return env.getProperty("rabbitMQ.exchange.type");
	}
	
	@Bean(name = "notificationsQueue")
	public String notificationsQueue() {
		return env.getProperty("rabbitMQ.queue.notifications");
	}

	@Bean(name = "notificationsQueueKey")
	public String notificationsQueueKey() {
		return env.getProperty("rabbitMQ.key.notifications");
	}

	@Bean(name = "subscriptionsQueue")
	public String subscriptionsQueue() {
		return env.getProperty("rabbitMQ.queue.subscriptions");
	}

	@Bean(name = "subscriptionsQueueKey")
	public String subscriptionsQueueKey() {
		return env.getProperty("rabbitMQ.key.subscriptions");
	}

	@Bean(name = "communicationProducer")
	public CommunicationProducerImpl communicationProducer(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
		Map<String, Set<String>> queuesAndKeys = new HashMap<>();
		queuesAndKeys.put(notificationsQueue(), toSet(notificationsQueueKey()));
		queuesAndKeys.put(subscriptionsQueue(), toSet(subscriptionsQueueKey()));
		System.out.println(connectionFactory.getUsername());
		return new CommunicationProducerImpl(connectionFactory,
				exchangeName(), exchangeType(), queuesAndKeys);
	}
	
	private static <T> Set<T> toSet(T element) {
		Set<T> list = new HashSet<>();
		list.add(element);
		return list;
	}
}
