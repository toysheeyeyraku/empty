package com.gemicle.events;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.gemicle.messaging.entity.NotificationData;
import com.gemicle.messaging.entity.NotificationMessage;

import lombok.Data;

@Data
public class PrepareSendNotificationEvent {
	private ObjectId accountId;
	private NotificationMessage message;
	private Map<String,Object> args;
	
}
