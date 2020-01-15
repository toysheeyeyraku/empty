package test.notificationsend;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.gemicle.events.PrepareSendNotificationEvent;
import com.gemicle.messaging.entity.NotificationData;
import com.gemicle.messaging.enums.NotificationType;

@RestController
public class Controlls {
	@Autowired
	@Qualifier("amqpOutboundChannel")
	private MessageChannel channel;
	@GetMapping("sendfull")
	public String zonne() {
		PrepareSendNotificationEvent event =new PrepareSendNotificationEvent();
		event.setAccountId(new ObjectId("5e04c6f064e9c2116cc0117b"));
		NotificationData data =new NotificationData();
		data.setNotificationType(NotificationType.REBOOT);
		event.setMessage(data);
		channel.send(MessageBuilder.withPayload(event).build());
		return "g";
	}
	@GetMapping("t")
	public String h(@RequestHeader HttpHeaders headers) {
		
		System.out.println(headers.get("Authorization"));
		return "h";
	}
}
