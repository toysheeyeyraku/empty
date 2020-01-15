package com.gemicle.eventing;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.inbound.AmqpInboundChannelAdapter;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.gateway.GatewayProxyFactoryBean;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gemicle.kovtun.eventing.config.EventingConsumerConfiguration;
import com.gemicle.kovtun.eventing.config.EventingProducerConfiguration;
import com.google.common.eventbus.EventBus;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EventingProducerConfiguration.class, EventingConsumerConfiguration.class })
@TestPropertySource(locations = "classpath:test.properties")

public class AppTest extends TestCase {

	@Autowired

	@Qualifier("inboundChannelAdapter")
	private AmqpInboundChannelAdapter adapter;

	@Autowired
	private EventBus bus;

	@Bean("amqpInbound")
	public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
		return IntegrationFlows.from(adapter).transform(Transformers.fromJson()).handle(m -> bus.post(m.getPayload()))
				.get();
	} /*
		 * Consumer
		 * 
		 * 
		 * /* Producer
		 */

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

	
	
	@Autowired
	private EventProducermanager gate;
	
	@org.junit.Test
	public void whenFindByName_thenReturnEmployee() {
		gate.send("gf");
		
	}
	@MessagingGateway(defaultRequestChannel = "amqpOutboundChannel")
	 interface EventProducermanager {
		
		void send(Object obj);

	}
	 
	/*@Bean
    public GatewayProxyFactoryBean gateway() {
        GatewayProxyFactoryBean gateway = new GatewayProxyFactoryBean(EventProducermanager.class);
        gateway.setDefaultRequestChannel(channel);
        
        return gateway;
    }*/
}
