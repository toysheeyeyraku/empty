package com.gemicle.mailingsettings.entity;

import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gemicle.messaging.enums.NotificationType;

import lombok.Data;

@Document(collection =  "mailingsettings")
@Data
public class MailingSettings {
	@Id
	private ObjectId mailingSettingsId;
	private ObjectId accountId;                			//id акаунта , що має ці настройки
	private Map<NotificationType,EnabledMailingChannels> enabledChannels;
	private Set<NotificationType> notificationEnabled;  //Включені нотифікації
	
	
}
