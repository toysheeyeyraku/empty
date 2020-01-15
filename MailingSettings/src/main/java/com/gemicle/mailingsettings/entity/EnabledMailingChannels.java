package com.gemicle.mailingsettings.entity;

import java.util.Set;

import com.gemicle.mailingsettings.enums.MailingType;

import lombok.Data;
@Data
public class EnabledMailingChannels {
	private Set<MailingType> channels;
	public boolean containsMailingType(MailingType mailingType) {
		return channels.contains(mailingType);
	}
}
