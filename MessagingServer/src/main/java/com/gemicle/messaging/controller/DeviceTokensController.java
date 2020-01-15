package com.gemicle.messaging.controller;

import com.gemicle.messaging.enums.ChannelType;
import com.gemicle.messaging.enums.NewsType;
import com.gemicle.messaging.service.MessageSettingsService;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gemicle.messaging.entity.DeviceToken;
import com.gemicle.messaging.entity.UserDeviceTokens;
import com.gemicle.messaging.service.ConsumerMessageService;
import com.gemicle.messaging.service.DeviceTokensService;
import com.gemicle.users.model.Credentials;
import com.gemicle.users.security.service.AuthoritySecurityService;

import java.util.Collections;
import java.util.Set;

@Api
@RestController
@RequestMapping("/deviceToken")
public class DeviceTokensController {
	
	@Autowired
	private DeviceTokensService deviceTokensService;
	@Autowired
	private ConsumerMessageService consumerMessageService;
	@Autowired
	private MessageSettingsService messageSettingsService;
    @Autowired
    @Qualifier("authoritySecurityService")
    private AuthoritySecurityService authoritySecurityService;
	
	@PostMapping("/save")
	public UserDeviceTokens saveToken(HttpServletRequest request, @RequestParam(required=false) ObjectId carrierId, @RequestBody DeviceToken deviceToken) {
		carrierId = authoritySecurityService.checkReadAccessToUserOrReturnCurrentUserCarrier(carrierId);
		Credentials credentials = getCredentials();
		//consumerMessageService.sendSubscription(deviceToken.getToken(), true, NewsType.getAllTopicsName());
		return deviceTokensService.saveDeviceToken(deviceToken, credentials.getId(), request.getLocale());
	}
	
	@PostMapping("/update")
	public UserDeviceTokens saveToken(HttpServletRequest request, @RequestParam(required=false) ObjectId carrierId, @RequestParam(required=true) String oldToken, @RequestBody DeviceToken deviceToken) {
		carrierId = authoritySecurityService.checkReadAccessToUserOrReturnCurrentUserCarrier(carrierId);
		Credentials credentials = getCredentials();
		//consumerMessageService.sendSubscription(oldToken, false, NewsType.getAllTopicsName());
		Set<ChannelType> newsChannels = messageSettingsService.findByCredentialsId(credentials.getId()).getNewsChannels();
		if (newsChannels.contains(ChannelType.MOBILE)) {
		//	consumerMessageService.sendSubscription(deviceToken.getToken(), true, NewsType.getAllTopicsName());
		}
		return deviceTokensService.updateDeviceToken(deviceToken, oldToken, carrierId, credentials.getId(), request.getLocale());
	}

	@PostMapping("/unsubscribe")
	public void updateSubscriptions(@RequestParam(required=false) ObjectId carrierId, @RequestParam(required=true) String deviceToken) {
		carrierId = authoritySecurityService.checkReadAccessToUserOrReturnCurrentUserCarrier(carrierId);
		Credentials credentials = getCredentials();
		messageSettingsService.removeChannels(credentials.getId(), Collections.singletonList(ChannelType.MOBILE));
		//consumerMessageService.sendSubscription(deviceToken, false, NewsType.getAllTopicsName());
	}
	
	@DeleteMapping("/delete")
	public void remove(@RequestParam(required=false) ObjectId carrierId, @RequestParam(required=true) String deviceToken) {
		carrierId = authoritySecurityService.checkReadAccessToUserOrReturnCurrentUserCarrier(carrierId);
		Credentials credentials = getCredentials();
		//consumerMessageService.sendSubscription(deviceToken, false, NewsType.getAllTopicsName());
		deviceTokensService.deleteDeviceToken(credentials.getId(), deviceToken);
	}
	
	private Credentials getCredentials(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (Credentials) auth.getPrincipal();
	}
}
