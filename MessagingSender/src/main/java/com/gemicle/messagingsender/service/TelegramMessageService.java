package com.gemicle.messagingsender.service;


import org.springframework.stereotype.Service;

import com.gemicle.messagingsender.bot.NotificationBot;
import com.gemicle.messagingsender.model.MessageObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TelegramMessageService implements MessageService {

    private final NotificationBot notificationBot;

    public TelegramMessageService(NotificationBot notificationBot) {
        this.notificationBot = notificationBot;
    }

    @Override
    public void sendMessage(MessageObject messageObject) {
        notificationBot.sendMessage(messageObject);
    }
}
