package com.gemicle.mailingsettings.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gemicle.mailingsettings.entity.MailingSettings;
import com.gemicle.messaging.enums.NotificationType;

public interface MailingSettingsRepository extends MongoRepository<MailingSettings, ObjectId> {
	MailingSettings findByAccountId(ObjectId accountId);
	MailingSettings findByAccountIdAndNotificationEnabledContaining(ObjectId accountId,NotificationType notificationType);
	
}
