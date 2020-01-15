package com.gemicle.messaging.service;

import com.gemicle.messaging.enums.ChannelType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gemicle.messaging.entity.MessageSettings;

import java.util.List;

public interface MessageSettingsService {

	MessageSettings save(MessageSettings messageSettings);

	MessageSettings findByCredentialsId(ObjectId credentialsId);

	Page<MessageSettings> findAll(Pageable pageable);

	MessageSettings removeChannels(ObjectId credentialId, List<ChannelType> channels);
}
