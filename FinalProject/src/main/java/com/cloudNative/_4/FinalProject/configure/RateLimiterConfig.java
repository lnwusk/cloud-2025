package com.cloudNative._4.FinalProject.configure;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {


    @Bean
    public RateLimiter rateLimiter() {
        // 每秒产生1个令牌
        return RateLimiter.create(1.0);
    }

//    @Bean
//    public KeyResolver userKeyResolver() {
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//            }
//        };
//    }
}