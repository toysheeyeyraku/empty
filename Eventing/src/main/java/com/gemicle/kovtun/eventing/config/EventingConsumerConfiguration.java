package com.gemicle.kovtun.eventing.config;

import java.util.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.gemicle.eventing.kovtun.eventhandler.AutoRegisteredEventHandler;
import com.google.common.eventbus.EventBus;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description goes here.
 *
 * @version 1.0.0 20.12.2019
 * @author Kovtun Bogdan
 */

@Configuration
@Slf4j
public class EventingConsumerConfiguration {
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

	@Value("${eventing.queue.name}")
	private String queueName;
	@Value("${eventing.routingKey}")
	private String routingKey;
	@Value("${eventing.exchangeName}")
	private String exchangeName;

	/**
	 * Spring automaically puts all AutoRegisteredEventHandler interface
	 * implementations to List that has @Component annotation
	 */
	@Autowired(required = false)
	public List<AutoRegisteredEventHandler> eventHandlers;

	/**
	 * Here creates EventBus and here all AutoRegisteredEventHandlers is auto
	 * registered
	 */
	@Bean("eventingEventBus")
	public EventBus eventBus() {
		EventBus bus = new EventBus();
		if (eventHandlers != null) {
			for (AutoRegisteredEventHandler h : eventHandlers) {
				log.info("EventHandler registered-->" + h.getClass().toString());
				bus.register(h);
			}
		} else {
			log.info("EventHandlers not found");
		}
		return bus;
	}

	@Bean(name = "inboundChannelAdapter")
	public AmqpInboundChannelAdapter inbound(
			@Qualifier("simpleListenerContainer") SimpleMessageListenerContainer listenerContainer,
			@Qualifier("amqpInboundChannel") MessageChannel channel) {
		AmqpInboundChannelAdapter adapter = new AmqpInboundChannelAdapter(listenerContainer);
		adapter.setOutputChannel(channel);

		return adapter;
	}

	@Bean("simpleListenerContainer")
	public SimpleMessageListenerContainer container(
			@Qualifier("eventingConsumerConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(eventingConsumeQueue());

		return container;
	}

	@Bean("eventingConsumeQueue")
	public Queue eventingConsumeQueue() {
		return new Queue(queueName, false, false, false);
	}

	@Bean("eventingBinding")
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	@Bean("eventingExchange")
	public TopicExchange exchange() {
		TopicExchange topicExchange = new TopicExchange(exchangeName);
		topicExchange.setShouldDeclare(true);
		return topicExchange;
	}

	@Bean("amqpInboundChannel")
	public MessageChannel amqpInboundChannel() {
		return new DirectChannel();
	}

	@Bean("eventingConsumerConnectionFactory")
	public ConnectionFactory connectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHostName);

		log.info("Created connection factory with---->" + "Host=" + rabbitmqHostName + " " + "Port=" + port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setPort(port);

		return connectionFactory;
	}

}
