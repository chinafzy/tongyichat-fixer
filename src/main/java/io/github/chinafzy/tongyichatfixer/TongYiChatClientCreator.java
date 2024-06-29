package io.github.chinafzy.tongyichatfixer;

import com.alibaba.cloud.ai.tongyi.TongYiAutoConfiguration;
import com.alibaba.cloud.ai.tongyi.TongYiConnectionProperties;
import com.alibaba.cloud.ai.tongyi.chat.TongYiChatClient;
import com.alibaba.cloud.ai.tongyi.chat.TongYiChatProperties;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.common.MessageManager;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public class TongYiChatClientCreator {
    private final TongYiAutoConfiguration conf;
    private final TongYiChatProperties chatOptions;
    private final TongYiConnectionProperties connectionProperties;

    /**
     * 创建一个通义聊天客户端
     *
     * @param historySize 历史消息记录大小
     * @return 通义聊天客户端
     */
    public TongYiChatClient create(int historySize) {
        TongYiChatClient client = conf.tongYiChatClient(new Generation(), chatOptions, connectionProperties);
        setMsgManager(client, new MessageManager(historySize));

        return client;
    }

    /**
     * 创建一个通义聊天客户端，消息记录大小为10
     *
     * @return 通义聊天客户端
     */
    public TongYiChatClient create() {
        return create(10);
    }

    private static void setMsgManager(TongYiChatClient client, MessageManager msgManager) {
        try {
            Field field = client.getClass().getDeclaredField("msgManager");
            field.setAccessible(true);
            field.set(client, msgManager);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
