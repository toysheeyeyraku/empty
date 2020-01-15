package com.gemicle.messaging.service.impl;

import com.gemicle.messaging.enums.ChannelType;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gemicle.messaging.entity.MessageSettings;
import com.gemicle.messaging.repositories.MessageSettingsRepository;
import com.gemicle.messaging.service.MessageSettingsService;

import java.util.List;

@Service
public class MessageSettingsServiceImpl implements MessageSettingsService {
	
	@Autowired
	private MessageSettingsRepository messageSettingsRepository;
	
	@Override
	public MessageSettings save(MessageSettings messageSettings){
		return messageSettingsRepository.save(messageSettings);
	}
	@Override
	public MessageSettings findByCredentialsId(ObjectId credentialsId){
		return messageSettingsRepository.findOne(credentialsId);
	}
	@Override
	public Page<MessageSettings> findAll(Pageable pageable){
		return messageSettingsRepository.findAll(pageable);
	}

	@Override
	public MessageSettings removeChannels(ObjectId credentialId, List<ChannelType> channels) {
		MessageSettings settings = messageSettingsRepository.findOne(credentialId);
		channels.forEach(x->settings.getNewsChannels().remove(x));
		return messageSettingsRepository.save(settings);
	}
}
