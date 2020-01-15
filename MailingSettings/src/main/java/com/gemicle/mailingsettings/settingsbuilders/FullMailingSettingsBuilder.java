package com.gemicle.mailingsettings.settingsbuilders;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.bson.types.ObjectId;

import com.gemicle.mailingsettings.entity.EnabledMailingChannels;
import com.gemicle.mailingsettings.entity.MailingSettings;
import com.gemicle.mailingsettings.enums.MailingType;
import com.gemicle.messaging.enums.NotificationType;

public class FullMailingSettingsBuilder {
	public static MailingSettings build(ObjectId accountId) {
		MailingSettings settings = new MailingSettings();

		Set<MailingType> all = new TreeSet<MailingType>();
		all.add(MailingType.EMAIL);
		all.add(MailingType.SMS);
		all.add(MailingType.TELEGRAM);
		all.add(MailingType.VIBER);
		all.add(MailingType.FIREBASE);
		TreeMap<NotificationType, EnabledMailingChannels> mailingChannels = new TreeMap<NotificationType, EnabledMailingChannels>();
		EnabledMailingChannels ch = new EnabledMailingChannels();
		ch.setChannels(all);
		
		mailingChannels.put(NotificationType.UNAVAILABLE, ch);
		mailingChannels.put(NotificationType.NEWS, ch);
		mailingChannels.put(NotificationType.REBOOT, ch);
		mailingChannels.put(NotificationType.ZONE, ch);
		settings.setEnabledChannels(mailingChannels);
		Set<NotificationType> notificationsEnabled = new TreeSet<NotificationType>();
		notificationsEnabled.add(NotificationType.REBOOT);
		notificationsEnabled.add(NotificationType.NEWS);
		notificationsEnabled.add(NotificationType.ZONE);
		notificationsEnabled.add(NotificationType.UNAVAILABLE);
		settings.setNotificationEnabled(notificationsEnabled);
		settings.setAccountId(accountId);
		return settings;
	}
}
