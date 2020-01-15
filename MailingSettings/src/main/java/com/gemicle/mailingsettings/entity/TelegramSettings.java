package com.gemicle.mailingsettings.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection =  "telegramsettings")
@Data
public class TelegramSettings {
	@Id
	private ObjectId accountId;                			//id акаунта , що має ці настройки
	private String channelId;
	
	
	
}