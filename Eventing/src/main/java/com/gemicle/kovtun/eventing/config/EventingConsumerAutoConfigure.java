package com.gemicle.kovtun.eventing.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Transformers;

import com.google.common.eventbus.EventBus;
@Configuration
public class EventingConsumerAutoConfigure {
	@Autowired

	@Qualifier("inboundChannelAdapter")
	private AmqpInboundChannelAdapter adapter;

	@Autowired
	private EventBus bus;

	@Bean("amqpInbound")
	public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
		return IntegrationFlows.from(adapter).transform(Transformers.fromJson()).handle(m -> bus.post(m.getPayload()))
				.get();
	} 
}
