Feign
1.jackson -> fastJson			需要自定义FastJsonEncoder, FastJsonDecoder
	默认 SpringEncoder, ResponseEntityDecoder
2.RemoteService service = Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .options(new Request.Options(1000, 3500))
            .retryer(new Retryer.Default(5000, 5000, 3))
            .target(RemoteService.class, "http://127.0.0.1:8087");
  -> 支持可配置
3.返回值协议
4.断路器

默认connectTimeout-连接超时时长100
默认readTimeout-响应超时时长60s
默认重试5次



---------------------------- 2018.06.05 讨论feign实现 ---------------------------
1.get 2种方式、post 方式
2.@head是否支持自定义
3.配置文件地址
4.启动类、调用代码尽量简单化			OK
5.返回值格式 接口
6.filter拦截返回值，处理空、异常情况，抛异常



1.异步调用  @Async
2.o2o-common-dependency/common/o2o-common-dependency-common.iml 加依赖(feign,cubo-feign)
3.请求：path, query, header, body
4.响应：协议接口定义，异常包(怡富，及时跟进)、异常拦截器
5.@EnableFeignClients & 接口定义 写在common里面


移配置 -> 合分支 -> 整理文档


----------------------------- 2018.06.07 方案评审后 ---------------------------------
1.@EnableFeignClients 自己包装一下
2.common.propertise 超时时间等
	cookies默认禁用，https默认不校验
3.返回值协议接口
4.编解码, 默认fastJson
5.打点，httpClient加强包


