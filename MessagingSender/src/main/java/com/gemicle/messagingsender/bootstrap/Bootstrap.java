package com.gemicle.messagingsender.bootstrap;

/*import com.gemicle.messagingsender.clients.ViberClient;
import com.gemicle.messagingsender.model.MessageObject;
import com.gemicle.messagingsender.model.Webhook;
import com.gemicle.messagingsender.service.EmailMessageService;
import com.gemicle.messagingsender.service.TelegramMessageService;
import com.gemicle.messagingsender.service.ViberMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;*/

//@Component
//@Slf4j
//public class Bootstrap implements CommandLineRunner {
/*
    private final ViberMessageService viberMessageService;
    private final TelegramMessageService telegramMessageService;
    private final EmailMessageService emailMessageService;
*/
   /* @Value("${viber.bot.auth_token}")
    private String AUTH_TOKEN;

    @Value("${viber.bot.webhook_url}")
    private String WEBHOOK_URL;

    private final ViberClient viberClient;

    public Bootstrap(ViberMessageService viberMessageService, TelegramMessageService telegramMessageService, EmailMessageService emailMessageService, ViberClient viberClient) {*/
       /* this.viberMessageService = viberMessageService;
        this.telegramMessageService = telegramMessageService;
        this.emailMessageService = emailMessageService;*/
        /*this.viberClient = viberClient;
    }

    @Override
    public void run(String... args) {
        log.info(viberClient.getAccountInfo(AUTH_TOKEN));

        List<String> eventTypes = new ArrayList<>();
        eventTypes.add("delivered");
        eventTypes.add("seen");
        eventTypes.add("conversation_started");

        Webhook webhook = new Webhook();
        webhook.setUrl(WEBHOOK_URL);
        webhook.setEventTypes(eventTypes);

        log.info(viberClient.setWebhook(AUTH_TOKEN, webhook));*/
/*
        MessageObject viberMessage = new MessageObject();
        viberMessage.setMessage("Viber Message");
        List<String> viberIds = new ArrayList<>();
        viberIds.add("KH3dZ4R1taQMx0GyDBEqQQ==");
        viberMessage.setRecipients(viberIds);

        MessageObject telegramMessage = new MessageObject();
        telegramMessage.setMessage("Telegram Message");
        List<String> chatIds = new ArrayList<>();
        chatIds.add("228066103");
        telegramMessage.setRecipients(chatIds);

        MessageObject emailMessage = new MessageObject();
        emailMessage.setMessage("Email Message");
        List<String> emails = new ArrayList<>();
        emails.add("m.shevchuk.o@gmail.com");
        emailMessage.setRecipients(emails);

        viberMessageService.sendMessage(viberMessage);
        telegramMessageService.sendMessage(telegramMessage);
        emailMessageService.sendMessage(emailMessage);
*/

//    }
//}