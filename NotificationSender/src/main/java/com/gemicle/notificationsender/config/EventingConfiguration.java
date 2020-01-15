package com.gemicle.notificationsender.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.MessageChannel;

import com.gemicle.mailingsettings.services.IMailingSettings;
import com.gemicle.messagingsender.model.MessageObject;
import com.gemicle.messagingsender.service.EmailMessageService;
import com.gemicle.messagingsender.service.MessageService;
import com.gemicle.messagingsender.service.TelegramMessageService;
import com.gemicle.notificationsender.dto.MessageToSendDTO;
import com.gemicle.notificationsender.flowhandlers.HandleSendEvent;
import com.gemicle.notificationsender.services.MailingSettingsRouter;


@Configuration
public class EventingConfiguration {
	@Autowired
	private MailingSettingsRouter router;
	@Autowired
	private IMailingSettings mailingSettings;
	/*
	 * @MessagingGateway(defaultRequestChannel = "notification.send.channel") public
	 * interface EventConsumermanager {
	 * 
	 * void send(Object obj);
	 * 
	 * }
	 */

	@Bean("eventChannel")
	public MessageChannel eventChannel() {
		System.out.println("ZAPUSK");
		return new DirectChannel();
	}

	@Bean("telegramChannel")
	public DirectChannel telegramChannel() {
		return new DirectChannel();
	}

	@Bean("viberChannel")
	public DirectChannel viberChannel() {
		return new DirectChannel();
	}

	@Bean("SMSChannel")
	public DirectChannel SMSChannel() {
		return new DirectChannel();
	}

	@Bean("FirebaseChannel")
	public DirectChannel FirebaseChannel() {
		return new DirectChannel();
	}

	@Bean("GMAILChannel")
	public DirectChannel GMAILChannel() {
		return new DirectChannel();
	}

	@Bean("defaultMailing")
	public DirectChannel defaultMailing() {
		return new DirectChannel();
	}

	@Bean("notificationFlow")
	public IntegrationFlow notificationChannel() {
		return IntegrationFlows.from(eventChannel()).handle(new HandleSendEvent(mailingSettings)).split()
				.route(router).get();
	}
	private MessageObject f(Object object) {
		MessageToSendDTO dto=(MessageToSendDTO)object;
		MessageObject msg =new MessageObject();
        msg.setMessage(dto.getMessage().getBody());
        List<String> zns=new ArrayList<String>() ;
        zns.add("695333050");
        msg.setRecipients(zns);
        return msg;
	}
	private MessageObject ff(Object object) {
		MessageObject msg =new MessageObject();
		MessageToSendDTO dto=(MessageToSendDTO)object;
        msg.setMessage(dto.getMessage().getBody());
        List<String> zns=new ArrayList<String>() ;
        zns.add("kirpich1337228@gmail.com");
        msg.setRecipients(zns);
        return msg;
	}
	@Autowired
	private TelegramMessageService telegramMessageService;
	@Autowired
	private EmailMessageService emailMessageService;
	@Bean("telegramFlow")
	public IntegrationFlow telegramFlow() {
		return IntegrationFlows.from("telegramChannel").handle(p -> telegramMessageService.sendMessage(f(p.getPayload()))).get();
	}

	@Bean("viberFlow")
	public IntegrationFlow viberFlow() {
		return IntegrationFlows.from("viberChannel").handle(p -> System.out.println(p.getPayload())).get();
	}
	@Bean("SMSFlow")
	public IntegrationFlow SMSFlow() {
		return IntegrationFlows.from("SMSChannel").handle(p -> System.out.println(p.getPayload())).get();
	}
	@Bean("FirebaseFlow")
	public IntegrationFlow FirebaseFlow() {
		return IntegrationFlows.from("FirebaseChannel").handle(p -> System.out.println(p.getPayload())).get();
	}
	@Bean("GMAILFlow")
	public IntegrationFlow GMAILFlow() {
		return IntegrationFlows.from("GMAILChannel").handle(p -> emailMessageService.sendMessage(ff(p.getPayload()))).get();
	}
	@Bean("defaultMailingFlow")
	public IntegrationFlow np2() {
		return IntegrationFlows.from("defaultMailing").handle(p -> System.out.println("d"+p.getPayload())).get();
	}
	

}
