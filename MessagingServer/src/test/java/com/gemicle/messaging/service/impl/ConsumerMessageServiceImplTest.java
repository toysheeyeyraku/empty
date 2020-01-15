package com.gemicle.messaging.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemicle.messagereceivers.rabbit.connection.producers.CommunicationProducer;
import com.gemicle.messaging.entity.NotificationMessage;
import com.gemicle.messaging.entity.SubscriptionMessage;
import com.gemicle.messaging.enums.NewsType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerMessageServiceImplTest {

    @Spy
    @InjectMocks
    private ConsumerMessageServiceImpl consumerMessageService;
    @Mock
    private CommunicationProducer communicationProducer;
    @Mock
    private ObjectMapper objectMapper;
    @Captor
    private ArgumentCaptor<SubscriptionMessage> captor;

    private String notificationsQueueKey = "notificationsQueueKey";
    private String subscriptionsQueueKey = "subscriptionsQueueKey";

    private List<String> topicList = NewsType.getAllTopicsName();
    private static final String deviceToken = "1234567890x1";

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(consumerMessageService, "notificationsQueueKey", notificationsQueueKey);
        ReflectionTestUtils.setField(consumerMessageService, "subscriptionsQueueKey", subscriptionsQueueKey);
    }

    @Test
    public void sendMessages() throws IOException {
        List<NotificationMessage> notifications = mock(List.class);
        consumerMessageService.sendMessages(notifications);
        verify(objectMapper,times(1)).writeValueAsBytes(notifications);
        verify(communicationProducer,times(1)).sendMessage(any(),eq(notificationsQueueKey));
    }

    @Test
    public void subscribeToTopics() throws IOException{
        consumerMessageService.sendSubscription(deviceToken, true, topicList);
        verify(objectMapper).writeValueAsBytes(captor.capture());
        verify(objectMapper, times(1)).writeValueAsBytes(Matchers.<SubscriptionMessage>any());
        verify(communicationProducer,times(1)).sendMessage(any(),eq(subscriptionsQueueKey));

        SubscriptionMessage message = captor.getValue();
        Assert.assertTrue( message.isSubscribeOn());
        Assert.assertEquals(deviceToken, message.getDeviceToken());
        Assert.assertEquals(topicList.size(), message.getTopicList().size());
    }

    @Test
    public void unsubscribeFromTopic() throws IOException {
        consumerMessageService.sendSubscription(deviceToken, false, topicList);
        verify(objectMapper).writeValueAsBytes(captor.capture());

        SubscriptionMessage message = captor.getValue();
        Assert.assertFalse( message.isSubscribeOn());
    }
}