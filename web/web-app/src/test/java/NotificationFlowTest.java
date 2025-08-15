import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoxin.blog.AppApplication;
import com.xiaoxin.blog.model.entity.Notification;
import com.xiaoxin.blog.model.enums.NotificationType;
import com.xiaoxin.blog.web.app.mapper.NotificationMapper;
import com.xiaoxin.blog.web.app.notifications.NotificationMessage;
import com.xiaoxin.blog.web.app.notifications.NotificationProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Objects;

@SpringBootTest(classes = AppApplication.class )
class NotificationFlowTest {

    @Autowired
    private NotificationProducer producer;

    @Autowired
    private NotificationMapper notificationMapper;

    @Test
    void sendAndConsume() throws Exception {
        NotificationMessage msg = NotificationMessage.builder()
                                                     .receiverId(1L)
                                                     .type(NotificationType.COMMENT)
                                                     .title("Hi")
                                                     .content("Test")
                                                     .payloadJson("{\"postId\":123}")
                                                     .createdAt(new Date())
                                                     .build();

        producer.send(msg);

        // 简单轮询等待消费者插入（最多等3秒）
        long end = System.currentTimeMillis() + 3000;
        Notification latest = null;
        while (System.currentTimeMillis() < end) {
            latest = notificationMapper.selectOne(new LambdaQueryWrapper<Notification>()
                    .eq(Notification::getReceiverId, 1L)
                    .orderByDesc(Notification::getId)
                    .last("limit 1"));
            if (latest != null && Objects.equals(latest.getTitle(), "Hi")) break;
            Thread.sleep(200);
        }

        Assertions.assertNotNull(latest, "消息未入库，检查消费者/队列绑定/JSON转换器");
        Assertions.assertEquals(0, latest.getIsRead().intValue());
    }
}