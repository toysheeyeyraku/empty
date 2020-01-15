package com.gemicle.messagingsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gemicle.messagingsender.model.MessageObject;

@Service
public class EmailMessageService implements MessageService {
	@Autowired
	private  JavaMailSender javaMailSender;

	
	@Override
	public void sendMessage(MessageObject messageObject) {
		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setBcc(messageObject.getRecipients().toArray(new String[0]));
		msg.setSubject("Notification");
		msg.setText(messageObject.getMessage());
		javaMailSender.send(msg);
	}
}
