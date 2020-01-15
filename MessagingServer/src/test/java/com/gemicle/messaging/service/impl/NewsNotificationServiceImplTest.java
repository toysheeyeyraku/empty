package com.gemicle.messaging.service.impl;

import com.gemicle.messaging.entity.NewsNotification;
import com.gemicle.messaging.entity.NotificationMessage;
import com.gemicle.messaging.enums.NewsType;
import com.gemicle.messaging.repositories.NewsNotificationRepository;
import com.gemicle.messaging.service.ConsumerMessageService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static  org.mockito.Mockito.*;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class NewsNotificationServiceImplTest {

    @Spy
    @InjectMocks
    private NewsNotificationServiceImpl newsNotificationService;
    @Mock
    private NewsNotificationRepository newsNotificationRepository;
    @Mock
    private ConsumerMessageService consumerMessageService;

    private ObjectId newsId = new ObjectId("590e76f9316d7919df7ad820");

    public static final String NEWS_ID="newsId";

    @Captor
    private ArgumentCaptor<List<NotificationMessage>> captor;
    @Captor
    private ArgumentCaptor<NewsNotification> captor1;

    @Test
    public void sendNotification() {
        when(newsNotificationRepository.findOne(newsId)).thenReturn(getNotification());

        boolean result = newsNotificationService.sendNotification(newsId);

        verify(consumerMessageService).sendMessages(captor.capture());
        verify(newsNotificationRepository).save(captor1.capture());

        Assert.assertTrue(result);
        List<NotificationMessage> list = captor.getValue();
        NewsNotification news = captor1.getValue();

        Assert.assertEquals(1, list.size());
        Assert.assertEquals(newsId.toHexString(), list.get(0).getData().get(NEWS_ID));
        Assert.assertTrue(news.isDelivered());
    }

    private NewsNotification getNotification(){
        NewsNotification news = new NewsNotification();
        news.setTitle("title");
        news.setDescription("description");
        news.setId(newsId);
        news.setTopic(NewsType.NEWS);
        return news;
    }

}