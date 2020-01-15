package com.gemicle.kovtun.eventing.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @version 1.0.0 20.12.2019
 * @author Kovtun Bogdan
 */

@Configuration
@Slf4j
public class EventingProducerConfiguration {

	/** Rabbitmq settings */

	@Value("${eventing.rabbitmq.hostname}")
	private String rabbitmqHostName;
	@Value("${eventing.rabbitmq.port}")
	private Integer port;
	@Value("${eventing.rabbitmq.username}")
	private String username;
	@Value("${eventing.rabbitmq.password}")
	private String password;

	/** Messaging settings */

	@Value("${eventing.routingKey}")
	private String routingKey;
	@Value("${eventing.exchangeName}")
	private String exchangeName;

	@Bean("amqpOutboundChannel")
	public MessageChannel amqpOutboundChannel() {
		return new DirectChannel();
	}

	@Bean("eventingOutboundAdapter")
	public AmqpOutboundEndpoint outboundAdapter(@Qualifier("eventingProducerAMQPTemplate") AmqpTemplate amqpTemplate) {
		AmqpOutboundEndpoint endpoint = new AmqpOutboundEndpoint(amqpTemplate);
		endpoint.setExchangeName(exchangeName);
		endpoint.setRoutingKey(routingKey);
		log.info("Created OutboundAdapter-->" + "ExchangeName=" + exchangeName + " " + "routingKey=" + routingKey);
		return endpoint;
	}

	@Autowired
	@Qualifier("eventingProducerConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Bean("eventingProducerAMQPTemplate")
	public AmqpTemplate amqpTemplate() {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMandatory(true);
		return rabbitTemplate;
	}

	@Primary
	@Bean("eventingProducerConnectionFactory")
	public ConnectionFactory eventingProducerConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHostName);
		log.info("Created connection factory with---->" + "Host=" + rabbitmqHostName + " " + "Port=" + port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setPort(port);

		return connectionFactory;
	}

}
