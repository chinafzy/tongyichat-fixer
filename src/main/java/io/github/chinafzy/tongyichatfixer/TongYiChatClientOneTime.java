package io.github.chinafzy.tongyichatfixer;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;


/**
 * 通义聊天客户端，只能处理一轮对话，不会保留历史记录。
 */
@RequiredArgsConstructor
public class TongYiChatClientOneTime implements ChatClient {
    private final TongYiChatClientCreator creator;

    @Override
    public ChatResponse call(Prompt prompt) {
        return creator.create(10).call(prompt);
    }

}
