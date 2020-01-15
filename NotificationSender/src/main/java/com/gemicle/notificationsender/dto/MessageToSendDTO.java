package com.gemicle.notificationsender.dto;

import java.util.Map;

import org.bson.types.ObjectId;

import com.gemicle.mailingsettings.enums.MailingType;
import com.gemicle.messaging.entity.NotificationMessage;
import lombok.Data;

@Data
public class MessageToSendDTO {
	private ObjectId accoutnId;
	private NotificationMessage message;
	private MailingType enabledMailingTypes;
	private Map<String,Object> args;
	
}
