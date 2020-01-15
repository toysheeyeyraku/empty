package com.gemicle.notificationsender.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.stereotype.Service;

import com.gemicle.mailingsettings.enums.MailingType;

@Service
public class MailingSettingsRouter extends RecipientListRouter {
	@Autowired
	@Qualifier("telegramChannel")
	private DirectChannel telegramChannel;
	@Autowired
	@Qualifier("viberChannel")
	private DirectChannel viberChannel;
	@Autowired
	@Qualifier("SMSChannel")
	private DirectChannel SMSChannel;
	@Autowired
	@Qualifier("FirebaseChannel")
	private DirectChannel FirebaseChannel;
	@Autowired
	@Qualifier("GMAILChannel")
	private DirectChannel GMAILChannel;

	@PostConstruct
	private void init() {
		ArrayList<Recipient> ans = new ArrayList<RecipientListRouter.Recipient>();
		ans.add(new Recipient(telegramChannel, new RouteChecker(MailingType.TELEGRAM)));
		ans.add(new Recipient(viberChannel, new RouteChecker(MailingType.VIBER)));
		ans.add(new Recipient(SMSChannel,new RouteChecker(MailingType.SMS)));
		ans.add(new Recipient(FirebaseChannel,new RouteChecker(MailingType.FIREBASE)));
		ans.add(new Recipient(GMAILChannel,new RouteChecker(MailingType.EMAIL)));
		
		this.setRecipients(ans);
		this.setDefaultOutputChannelName("defaultMailing");
	}
}
