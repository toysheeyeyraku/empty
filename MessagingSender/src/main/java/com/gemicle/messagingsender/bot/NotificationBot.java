package com.gemicle.messagingsender.bot;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.gemicle.messagingsender.model.MessageObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationBot extends TelegramLongPollingBot {
	//@Autowired
	//private TelegramMessageService tel ;
    


   
   // @Autowired
   // private UserService userService;
    static {
		ApiContextInitializer.init();
	}
    @PostConstruct
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }
    
    public void sendMessage(MessageObject messageObject) {
    	
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setText(messageObject.getMessage());
        for (String chatId : messageObject.getRecipients()) {
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void execute(SendMessage sendMessage) throws TelegramApiException {
		this.sendMessage(sendMessage);
		
	}

	@Override
    public void onUpdateReceived(Update update) {
        org.telegram.telegrambots.api.objects.Message message = update.getMessage();
        
       
        /*tel.sendMessage(msg);
        if (message != null && message.hasText()) {
            log.info(message.getText());
            
            String[] data = message.getText().split("\\s");

            switch (data[0]) {
                case "/start":
                    if (data.length > 1 && userService.isUserInBase(data[1])) {
                        if (!userService.isChatInBase(message.getChatId().toString())) {
                            User user = userService.findUserByUserId(data[1]);
                            user.setChatId(message.getChatId().toString());
                            userService.save(user);
                        }
                    }
                    break;

                default:

            }
        }*/
    }

    @Override
    public String getBotUsername() {
    	
        return "VINTransportbot";
    }

    @Override
    public String getBotToken() {
        return "924681343:AAEgfCwHPv9X-paTWdcFOLrRdUa7bptoyLQ";
    }
}
