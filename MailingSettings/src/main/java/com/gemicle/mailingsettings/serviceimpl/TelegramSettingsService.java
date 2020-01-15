package com.gemicle.mailingsettings.serviceimpl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemicle.mailingsettings.entity.TelegramSettings;
import com.gemicle.mailingsettings.repositories.TelegramSettingsRepository;
import com.google.common.base.Optional;

@Service("telegramSettingsService")
public class TelegramSettingsService {
	@Autowired
	private TelegramSettingsRepository settingsRepository;
	public void saveTelegramSettings(TelegramSettings telegramSettings) {
		settingsRepository.save(telegramSettings);
	}
	public TelegramSettings getTelegramSettingsByAccountId(ObjectId accountId) {
		Optional<TelegramSettings> ans =settingsRepository.findByAccountId(accountId);
		if (ans.isPresent()) {
			return ans.get();
		}
		return null;
	}
}
