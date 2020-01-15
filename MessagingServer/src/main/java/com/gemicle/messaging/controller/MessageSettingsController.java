package com.gemicle.messaging.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemicle.messaging.entity.MessageSettings;
import com.gemicle.messaging.service.MessageSettingsService;

@RestController
@RequestMapping("/settings")
public class MessageSettingsController {
	
	@Autowired
	private MessageSettingsService messageSettingsService;
	
	@PostMapping
	public MessageSettings save(@RequestBody MessageSettings messageSettings){		
		return messageSettingsService.save(messageSettings);
	}
	
	@GetMapping("/{credentialsId}")
	public MessageSettings get(@PathVariable ObjectId credentialsId){
		return messageSettingsService.findByCredentialsId(credentialsId);
	}
	
	@GetMapping("/getAll")
	public Page<MessageSettings> getAll(@PageableDefault Pageable pageable){
		return messageSettingsService.findAll(pageable);
	}

}
