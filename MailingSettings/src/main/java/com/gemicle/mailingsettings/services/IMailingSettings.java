package com.gemicle.mailingsettings.services;

import org.bson.types.ObjectId;

import com.gemicle.mailingsettings.entity.MailingSettings;
import com.gemicle.messaging.enums.NotificationType;


public interface IMailingSettings {
	
	public void createNewAccountDefault(ObjectId accoutnId);
	public void createNewAccountWithSettings(MailingSettings settings);
	public void updateSettings(MailingSettings settings);
	public MailingSettings getByAccountId(ObjectId accountId);
	public void  deleteByAccountId(ObjectId accountId);
	public MailingSettings getByAccountIdAndNotificationType(ObjectId accountId, NotificationType type);
	
}
