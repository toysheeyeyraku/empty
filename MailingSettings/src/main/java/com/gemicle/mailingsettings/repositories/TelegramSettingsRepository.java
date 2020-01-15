package com.gemicle.mailingsettings.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gemicle.mailingsettings.entity.TelegramSettings;
import com.google.common.base.Optional;

public interface TelegramSettingsRepository extends MongoRepository<TelegramSettings, ObjectId> {
	Optional<TelegramSettings> findByAccountId(ObjectId accountId);
	
	
}
