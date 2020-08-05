package com.dxx.gateway.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * 忽略请求拦截器
 * 读取配置的请求地址，不对其进行路由
 * @author cp5
 * @date 2020年 08月05日 11:31:17
 */
@Component
@ConfigurationProperties(prefix = "ignored")
public class IgnoredFilter implements GlobalFilter, Ordered {

    private List<String> urls ;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path=exchange.getRequest().getPath().toString();

        AntPathMatcher antPathMatcher = new AntPathMatcher();

        for(String pattern:urls){

            //匹配成功，不进行路由，返回404
            if(antPathMatcher.match(pattern, path)){
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.NOT_FOUND);
                return response.setComplete();
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
