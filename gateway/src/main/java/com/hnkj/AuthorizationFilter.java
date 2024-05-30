package com.hnkj;

import com.hnkj.utils.JwtUtil;
import com.hnkj.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2)
public class AuthorizationFilter implements GlobalFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 如果请求路径中包含login，那么直接放行
        String path = request.getPath().toString();
        if(path.split("/wms")[1].equals("/user/login")){
            return chain.filter(exchange);
        }

        // 从请求中获取请求内容
        MultiValueMap<String, String> queryParams = request.getHeaders();
        String authorization = queryParams.getFirst("Authorization");
        // 从redis中获取之前存入的 token
        String token = stringRedisTemplate.opsForValue().get("token");
        if(authorization != null){
            if(token != null) {
                if (token.equals(authorization)) {
                    Map<String,Object> claims = JwtUtil.parseToken(authorization);
                    ThreadLocalUtil.set(claims);
                    // 如果相等的话，那么放行
                    return chain.filter(exchange);
                }
            }else{
                // 如果是redis中的token没有获取到，那么就是后端出了问题，那么直接返回响应码500错误
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return exchange.getResponse().setComplete();
            }
        }
        // 如果请求头为空或者请求头不匹配，那么拦截不通过，并设置响应码为401未登录
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

}
