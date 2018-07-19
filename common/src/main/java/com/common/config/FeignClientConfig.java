package com.common.config;

import com.common.codec.FastJsonDecoder;
import com.common.codec.FastJsonEncoder;
import feign.Client;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by sheying on 2018/06/12.
 */
@Configuration
public class FeignClientConfig {

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder() {
        return new FastJsonDecoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder() {
        return new FastJsonEncoder();
    }

    /**
     * 重试次数配置
     * @return
     */
    @Bean
    public Request.Options requestOptions(ConfigurableEnvironment env){
        // 连接超时时长默认5000ms, 响应超时时长默认5000ms
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 5000);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 5000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
    /**
     * 重试次数配置
     * @return
     */
    @Bean
    public Retryer feignRetryer(ConfigurableEnvironment env) {
        // 重试间隔默认100ms, 最大重试时间默认1000ms, 重试次数默认0次。
        int period = env.getProperty("feign.period", int.class, 100);
        int maxPeriod = env.getProperty("feign.maxPeriod", int.class, 1000);
        int maxAttempts = env.getProperty("feign.maxAttempts", int.class, 0);

        return new Retryer.Default(period, maxPeriod, maxAttempts);
    }

    @Bean
    @ConditionalOnMissingBean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
             SpringClientFactory clientFactory) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        return new LoadBalancerFeignClient(new Client.Default(ctx.getSocketFactory(),
                new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession sslSession) {
                        return true;
                    }
                }),
                cachingFactory, clientFactory);
    }
}
