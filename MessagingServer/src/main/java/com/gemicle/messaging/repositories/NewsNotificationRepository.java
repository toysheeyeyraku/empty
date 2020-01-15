package com.gemicle.messaging.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gemicle.messaging.entity.NewsNotification;

@Repository
public interface NewsNotificationRepository extends MongoRepository<NewsNotification, ObjectId> {
	long count();

}
