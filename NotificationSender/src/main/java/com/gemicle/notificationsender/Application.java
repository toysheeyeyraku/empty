package com.gemicle.notificationsender;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.gemicle.integration.graph.annotations.EnableEIPGraph;
import com.gemicle.kovtun.eventing.annotations.EnableEventingConsumer;
import com.gemicle.kovtun.eventing.annotations.EnableEventingConsumerAutoConfigure;


@EnableEventingConsumer
@EnableEventingConsumerAutoConfigure

@SpringBootApplication(scanBasePackages = {"com.gemicle.integration.graph.annotations","com.gemicle.messagingsender","com.gemicle.notificationsender","com.gemicle.mailingsettings"})
@EnableMongoRepositories(basePackages  = {"com.gemicle.mailingsettings.repositories","com.gemicle.messagingsender.repository"},mongoTemplateRef = "mainDBTemplate")
@EnableEIPGraph
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
       

        // Register our bot
        
    }

	public void run(String... args) throws Exception {
		/*PrepareSendNotificationEvent event =new PrepareSendNotificationEvent();
		event.setAccountId(new ObjectId("5e04c6f064e9c2116cc0117b"));
		NotificationData data =new NotificationData();
		data.setNotificationType(NotificationType.REBOOT_ALERT);
		event.setMessage(data);
		bus.post(event);*/
		
		
		
	}
}
