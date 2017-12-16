# 说明：下面说明是以java开发为例
- 1.将common文件夹、MsgConfig.properties放于src根目录下。
- 2.修改MsgConfig.properties配置文件对应的内容为可用参数。
- 3.方法入口：

```java
common.msg.util.MsgContainer
sendWapPushMsg(String url,String desc,String cusMsisdn)//发送web push短信；
sendMsg(String msg,String cusMsisdn)：发送SMS
```

- 4.“定时器.txt”记录的是长链接链路检查的基于spring的配置，如果使用java原生定时器可自行配置。

- 5.依赖包包括log4j.jar、quartz-1.5.2.jar，quartz-1.5.2.jar为定时器使用，除此之外无特殊依赖包
