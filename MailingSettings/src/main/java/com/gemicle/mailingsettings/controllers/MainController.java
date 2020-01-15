package com.gemicle.mailingsettings.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gemicle.mailingsettings.entity.MailingSettings;
import com.gemicle.mailingsettings.entity.TelegramSettings;
import com.gemicle.mailingsettings.serviceimpl.MailingSettingsService;
import com.gemicle.mailingsettings.serviceimpl.TelegramSettingsService;

import io.swagger.annotations.Api;

/*@RestController
@Api
public class MainController {
	@Autowired
	private MailingSettingsService mailingService;
	@Autowired
	private TelegramSettingsService telegramService;
	@PostMapping("createNew")
	public String createNewAccountId(@RequestParam ObjectId accountId) {
		mailingService.createNewAccountDefault(accountId);
		return "test";
	}
	@PostMapping("getMailingSettings")
	public MailingSettings getByAccountId(@RequestParam ObjectId accountId) {
		return mailingService.getByAccountId(accountId );
	}
	@PostMapping("saveTelegramSettings")
	public String saveTelegramSettings(@RequestBody TelegramSettings settings) {
		telegramService.saveTelegramSettings(settings);
		return "ok";
	}
	@PostMapping("getTelegramSettings")
	public TelegramSettings getTelegramSettings(@RequestParam ObjectId id) {
		
		return telegramService.getTelegramSettingsByAccountId(id);
	}
	
}*/
