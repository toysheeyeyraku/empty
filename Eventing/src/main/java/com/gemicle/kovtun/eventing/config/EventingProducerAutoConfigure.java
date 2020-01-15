package com.gemicle.kovtun.eventing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.messaging.MessageChannel;

@Configuration
public class EventingProducerAutoConfigure {
	@Autowired
	@Qualifier("eventingOutboundAdapter")
	private AmqpOutboundEndpoint point;
	@Autowired
	@Qualifier("amqpOutboundChannel")
	private MessageChannel channel;

	@Bean("amqpOutbound")
	public IntegrationFlow amqpOutbound() {
		return IntegrationFlows.from(channel).transform(Transformers.toJson()).handle(point).get();
	}

	

	@MessagingGateway(defaultRequestChannel = "amqpOutboundChannel")
	public interface IventSendler {

		void send(Object obj);

	}
}
