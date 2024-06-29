# 通义ChatClient修复

阿里的`spring-cloud-starter-alibaba-ai`在设计上有一些问题，导致了一些问题，这里进行修复。

它基于阿里的`dashscope-sdk-java`，其中的`MessageManager`只能服务于一个会话。
`TongYiChatClient`绑定了一个`MessageManager`，因此它也只能服务于一个会话。

但是阿里的`spring-cloud-starter-alibaba-ai`中生成的`TongYiChatClient`是全局单例的。

这就导致了一个问题：大家共享使用时候，历史记录就混乱了。

## 使用
在引入了`spring-cloud-starter-alibaba-ai`后，再引入本项目，就可以直接在项目中使用。

+ `tongYiChatClientCreator`
用于生成`tongyiChatClient`，由调用者自己来管理会话的生命周期。

```java 
@Resource
TongYiChatClientCreator clientCreator;

void test() {
    ChatClient chatClient = clientCreator.createChatClient();
    // ...
}
```

+ `TongYiChatClientOneTime`
只能用一次的`TongYiChatClient`，用于一次性的会话。

```java
@Resource
@Qualifier("tongYiChatClientOneTime")
ChatClient chatClient;
```


## TODO
+ `MessageManager` 不支持分布式，也不支持持久化。
而且不能自己去实现，因为它是final class。
**蠢不可及！！！**

