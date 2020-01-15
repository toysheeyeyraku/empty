package com.gemicle.messaging.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gemicle.messaging.enums.NotificationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gemicle.messaging.enums.ChannelType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class MessageSettings {
	@Id
	private ObjectId credentialsId;
	private ObjectId carrierId;
	private Set<ChannelType> newsChannels;
	private Map<NotificationType,Boolean> notificationEnableMap;

	public static MessageSettings of(ObjectId credentialsId, ObjectId carrierId){
		MessageSettings newSettings = new MessageSettings();
		newSettings.setCredentialsId(credentialsId);
		newSettings.setCredentialsId(carrierId);
		Set<ChannelType> channels = Stream.of(ChannelType.WEB, ChannelType.MOBILE).collect(Collectors.toSet());
		newSettings.setNewsChannels(channels);
		Map<NotificationType, Boolean> notificationStatus = new HashMap<>();
		notificationStatus.put(NotificationType.REBOOT, false);
		notificationStatus.put(NotificationType.UNAVAILABLE, false);
		notificationStatus.put(NotificationType.ZONE, false);
		newSettings.setNotificationEnableMap(notificationStatus);
		return newSettings;
	}
}
