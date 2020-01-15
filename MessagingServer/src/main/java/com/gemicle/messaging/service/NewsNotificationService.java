package com.gemicle.messaging.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.gemicle.messaging.entity.NewsNotification;

public interface NewsNotificationService {

	NewsNotification save(NewsNotification newsNotification);

	boolean sendNotification(ObjectId id);

	List<NewsNotification> findAllNewsByPage(Integer page, Integer limit);

	long getPageAmount(Integer limit);

	boolean deleteNews(ObjectId id);

	NewsNotification findNewsById(ObjectId id);

}
