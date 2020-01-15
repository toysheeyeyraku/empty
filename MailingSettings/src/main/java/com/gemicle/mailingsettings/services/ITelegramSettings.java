package com.gemicle.mailingsettings.services;

import org.bson.types.ObjectId;

import com.gemicle.mailingsettings.entity.TelegramSettings;
import com.google.common.base.Optional;

public interface ITelegramSettings {
	void saveTelegramSettings(TelegramSettings settings);
	void updateTelegramSettings(TelegramSettings settings);
	Optional<TelegramSettings> getTelegramSettingsByAccountId(ObjectId accountId);
}
