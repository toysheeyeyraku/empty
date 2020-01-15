package com.gemicle.messaging.entity;

import com.gemicle.messaging.enums.NewsType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class NewsNotification {
	
	public static final String TITLE_FIELD="title";
	public static final String DESCRIPTION_FIELD="description";
	public static final String CREATION_DATE="creationDate";
	
	@Id
	private ObjectId id;
	private String title;
	private NewsType topic;
	private String description;
	private boolean delivered;
	//TODO: додати дату створення та дату редагування
}
