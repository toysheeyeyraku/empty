package test.notificationsend;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.gemicle.events.PrepareSendNotificationEvent;
import com.gemicle.kovtun.eventing.annotations.EnableEventingProducer;
import com.gemicle.kovtun.eventing.annotations.EnableEventingProducerAutoConfigure;
import com.gemicle.messaging.entity.NotificationData;
import com.gemicle.messaging.enums.NotificationType;




@SpringBootApplication
@EnableEventingProducer
@EnableEventingProducerAutoConfigure
public class Application implements CommandLineRunner {
	@Autowired
	@Qualifier("amqpOutboundChannel")
	private MessageChannel channel;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(String... args) throws Exception {
		
		PrepareSendNotificationEvent event =new PrepareSendNotificationEvent();
		event.setAccountId(new ObjectId("5e04c6f064e9c2116cc0117b"));
		NotificationData data =new NotificationData();
		data.setNotificationType(NotificationType.REBOOT);
		event.setMessage(data);
		channel.send(MessageBuilder.withPayload(event).build());
		
		
	}
}
