package com.gemicle.mailingsettings.serviceimpl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemicle.mailingsettings.entity.MailingSettings;
import com.gemicle.mailingsettings.repositories.MailingSettingsRepository;
import com.gemicle.mailingsettings.services.IMailingSettings;
import com.gemicle.mailingsettings.settingsbuilders.FullMailingSettingsBuilder;
import com.gemicle.messaging.enums.NotificationType;

@Service("mailservice")
public class MailingSettingsService implements IMailingSettings {
	@Autowired
	private MailingSettingsRepository settingsRepository;

	@Override
	public void createNewAccountDefault(ObjectId accountId) {

		settingsRepository.save(FullMailingSettingsBuilder.build(accountId));
	}

	@Override
	public void createNewAccountWithSettings(MailingSettings settings) {
		settingsRepository.save(settings);

	}

	@Override
	public void updateSettings(MailingSettings settings) {
		settingsRepository.save(settings);

	}

	@Override
	public MailingSettings getByAccountIdAndNotificationType(ObjectId accountId, NotificationType type) {
		return settingsRepository.findByAccountIdAndNotificationEnabledContaining(accountId, type);
	}

	@Override
	public void deleteByAccountId(ObjectId accountId) {
		settingsRepository.delete(settingsRepository.findByAccountId(accountId));
		;

	}

	@Override
	public MailingSettings getByAccountId(ObjectId accountId) {
		return settingsRepository.findByAccountId(accountId);
		
	}

}
