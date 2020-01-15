package com.gemicle.messagingsender.service;
/*
import com.gemicle.messagingsender.clients.ViberClient;
import com.gemicle.messagingsender.model.MessageObject;
import com.gemicle.messagingsender.model.Sender;
import com.gemicle.messagingsender.model.ViberBroadcastMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ViberMessageService implements MessageService {

    @Value("${viber.bot.auth_token}")
    private String AUTH_TOKEN;

    private Sender sender;

    private final ViberClient viberClient;

    public ViberMessageService(ViberClient viberClient) {
        this.viberClient = viberClient;
        sender = new Sender();
        sender.setName("Notifier");
        sender.setAvatar("http://avatar.example.com");
    }

    @Override
    public void sendMessage(MessageObject messageObject) {
        ViberBroadcastMessage viberBroadcastMessage = new ViberBroadcastMessage();
        viberBroadcastMessage.setSender(sender);
        viberBroadcastMessage.setType("text");
        viberBroadcastMessage.setText(messageObject.getMessage());
        viberBroadcastMessage.setBroadcastList(messageObject.getRecipients());

        viberClient.sendBroadcastMessage(AUTH_TOKEN, viberBroadcastMessage);
    }
}*/
