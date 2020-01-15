package com.gemicle.messagingsender.repository;

import com.gemicle.messagingsender.document.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    boolean existsByChatId(String chatId);

    boolean existsByUserId(String userId);

    User findUserByUserId(String userId);
}
