package io.github.chinafzy.tongyichatfixer;

import com.alibaba.cloud.ai.tongyi.TongYiAutoConfiguration;
import com.alibaba.cloud.ai.tongyi.TongYiConnectionProperties;
import com.alibaba.cloud.ai.tongyi.chat.TongYiChatProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class TongYiChatFixerAutoConfiguration {

    @Bean
    TongYiChatClientCreator tongYiChatClientCreator(
            TongYiAutoConfiguration conf,
            TongYiChatProperties chatOptions,
            TongYiConnectionProperties connectionProperties) {
        return new TongYiChatClientCreator(conf, chatOptions, connectionProperties);
    }

    @Bean
    TongYiChatClientOneTime tongYiChatClientOneTime(TongYiChatClientCreator creator) {
        return new TongYiChatClientOneTime(creator);
    }
}
