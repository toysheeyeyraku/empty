package com.gemicle.messagingsender.service;


import com.gemicle.messagingsender.document.User;

public interface UserService {

    void save(User user);

    boolean isChatInBase(String chatId);

    boolean isUserInBase(String userId);

    User findUserByUserId(String userId);
    
}
