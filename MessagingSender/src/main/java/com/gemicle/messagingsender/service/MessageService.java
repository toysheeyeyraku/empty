package com.gemicle.messagingsender.service;

import com.gemicle.messagingsender.model.MessageObject;

public interface MessageService {

    void sendMessage(MessageObject messageObject);
}
