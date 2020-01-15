package com.gemicle.messaging.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gemicle.messaging.entity.NotificationMessage;
import com.gemicle.messaging.enums.MessageType;
import com.gemicle.messaging.enums.NewsType;
import com.gemicle.messaging.enums.NotificationType;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.gemicle.events.PrepareSendNotificationEvent;
import com.gemicle.messaging.entity.NewsNotification;
import com.gemicle.messaging.entity.NotificationData;
import com.gemicle.messaging.repositories.NewsNotificationRepository;
import com.gemicle.messaging.service.ConsumerMessageService;
import com.gemicle.messaging.service.NewsNotificationService;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NewsNotificationServiceImpl implements NewsNotificationService {
	public static final String NEWS_ID="newsId";
	
	@Autowired
	private NewsNotificationRepository newsNotificationRepository;
	
	@Autowired
	private ConsumerMessageService consumerMessageService;
	
	@Override
	public List<NewsNotification> findAllNewsByPage(Integer page, Integer limit){
		PageRequest request=new PageRequest(page, limit, Direction.DESC, NewsNotification.CREATION_DATE);
		Page<NewsNotification> result = newsNotificationRepository.findAll(request);
		return result.getContent();
	}
	
	@Override
	public NewsNotification findNewsById(ObjectId id){
		return newsNotificationRepository.findOne(id);
	}
	
	@Override
	public long getPageAmount(Integer limit){
		long dataCount=newsNotificationRepository.count();
		return ((dataCount % limit > 0) ? dataCount / limit + 1 : dataCount / limit);
		
	}
	
	@Override
	public boolean deleteNews(ObjectId id){
		newsNotificationRepository.delete(id);
		return newsNotificationRepository.findOne(id) == null;
	}
	
	@Override
	public NewsNotification save(NewsNotification newsNotification){
		if(newsNotification.getId()!=null){
			NewsNotification savedNews=findNewsById(newsNotification.getId());
			savedNews.setTitle(newsNotification.getTitle());
			savedNews.setDescription(newsNotification.getDescription());
			return newsNotificationRepository.save(savedNews);
		}
		return newsNotificationRepository.save(newsNotification);
	}
	@Autowired
	@Qualifier("amqpOutboundChannel")
	private MessageChannel channel;
	@Override
	public boolean sendNotification(ObjectId id){
		//TODO PC-3338: in further will need to send only news description with id
		NewsNotification newsNotification=newsNotificationRepository.findOne(id);
		/*
		if(newsNotification==null||newsNotification.getTitle().isEmpty()||newsNotification.getDescription().isEmpty()){
			log.error("No data to send push notification for id"+ id);
			return false;
		}
		Map<String, String> data = new HashMap<>();
		data.put(NEWS_ID, id.toHexString());
       // data.put(NotificationMessage.TITLE, newsNotification.getTitle());
       // data.put(NotificationMessage.BODY, newsNotification.getDescription());
        NotificationMessage news = new NotificationMessage();
        //TODO PC-3338: fix two lines below when news types will include
        NewsType topic = newsNotification.getTopic();
      //  news.setTopicType((topic!=null) ? topic : NewsType.NEWS);
		news.setData(data);
		//news.setMessageType(MessageType.NEWS);
		consumerMessageService.sendMessages(Lists.newArrayList(news));*/
		newsNotification.setDelivered(true);
		newsNotificationRepository.save(newsNotification);
		PrepareSendNotificationEvent event =new PrepareSendNotificationEvent();
		event.setAccountId(new ObjectId("5e04c6f064e9c2116cc0117b"));
		NotificationData data =new NotificationData();
		data.setBody(newsNotification.getDescription());
		data.setNotificationType(NotificationType.NEWS);
		event.setMessage(data);
		channel.send(MessageBuilder.withPayload(event).build());
		return true;
	}
}
