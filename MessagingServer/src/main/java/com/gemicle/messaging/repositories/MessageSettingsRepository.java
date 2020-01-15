package com.gemicle.messaging.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gemicle.messaging.entity.MessageSettings;

@Repository
public interface MessageSettingsRepository extends MongoRepository<MessageSettings, ObjectId> {
}
