package com.gemicle.notificationsender.flowhandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.integration.dsl.support.GenericHandler;

import com.gemicle.events.PrepareSendNotificationEvent;
import com.gemicle.mailingsettings.entity.MailingSettings;
import com.gemicle.mailingsettings.enums.MailingType;
import com.gemicle.mailingsettings.services.IMailingSettings;
import com.gemicle.notificationsender.dto.MessageToSendDTO;

public class HandleSendEvent implements GenericHandler<PrepareSendNotificationEvent> {

	private IMailingSettings mailingsettingsService;

	@Override
	public List<MessageToSendDTO> handle(PrepareSendNotificationEvent data, Map headers) {
		
		
		
		
		List<MessageToSendDTO> ans = new ArrayList<MessageToSendDTO>();
		if (data.getAccountId()!=null) {
			MailingSettings settings = mailingsettingsService.getByAccountIdAndNotificationType(data.getAccountId(),data.getMessage().getNotificationType());
			if (settings==null) {
				return ans;
			}
			for (MailingType mailType : settings.getEnabledChannels().get(data.getMessage().getNotificationType())
					.getChannels()) {
				MessageToSendDTO dto = new MessageToSendDTO();
				dto.setEnabledMailingTypes(mailType);
				dto.setArgs(new TreeMap<String, Object>());
				dto.setMessage(data.getMessage());
				dto.setAccoutnId(data.getAccountId());
				ans.add(dto);
			}
		}
		
			
		

		return ans;
	}

	public HandleSendEvent(IMailingSettings settings) {
		this.mailingsettingsService = settings;
	}

}
