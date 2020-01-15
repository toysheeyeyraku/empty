package com.gemicle.messaging.controller;

import io.swagger.annotations.Api;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import com.gemicle.events.PrepareSendNotificationEvent;
import com.gemicle.messaging.entity.NewsNotification;
import com.gemicle.messaging.entity.NotificationData;
import com.gemicle.messaging.enums.NotificationType;
import com.gemicle.messaging.service.NewsNotificationService;

import java.util.List;

@Api
@RestController
@RequestMapping("news")
public class NewsNotificationController {

	private static final String DEFAULT_LIMIT="20";
	private static final String DEFAULT_PAGE="0";
	
	@Autowired
	private NewsNotificationService newsNotificationService;

	@GetMapping("/totalPages")
	@ResponseBody
	public long pageAmount(@RequestParam(defaultValue = DEFAULT_LIMIT) Integer limit){
		return newsNotificationService.getPageAmount(limit);
	}

	@GetMapping("/all")
	@ResponseBody
	public List<NewsNotification> findAllNews(@RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
											  @RequestParam(defaultValue = DEFAULT_LIMIT) Integer limit){
		return newsNotificationService.findAllNewsByPage(page, limit);
	}

	@GetMapping("/getNewsById")
	public ResponseEntity<NewsNotification> getNewsById(@RequestParam ObjectId id){
		NewsNotification newsNotification = newsNotificationService.findNewsById(id);
		return new ResponseEntity<>(newsNotification, newsNotification != null ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
	}

	//TODO PC-3338 need to add "/update" endpoint or update "/save" endpoint
	@PostMapping("/save")
	public ResponseEntity<NewsNotification> saveNewsNotification(@RequestBody NewsNotification newsNotification){
		
		NewsNotification savedNotification=newsNotificationService.save(newsNotification);
		
		return new ResponseEntity<>(savedNotification, savedNotification != null ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping("/delete")
	public ResponseEntity deleteNews(@RequestParam ObjectId id){
		return new ResponseEntity(newsNotificationService.deleteNews(id) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping("/send")
	public ResponseEntity sendNewsNotification(@RequestParam ObjectId id){
		return new ResponseEntity(newsNotificationService.sendNotification(id) ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
	}
}
