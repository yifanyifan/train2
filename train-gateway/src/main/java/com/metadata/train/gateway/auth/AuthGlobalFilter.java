package com.metadata.train.gateway.auth;

import cn.hutool.core.util.StrUtil;
import com.metadata.train.auth.AuthConstant;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 全局过滤器AuthGlobalFilter
 * 当鉴权通过后将JWT令牌中的用户信息解析出来，然后存入请求的Header中，这样后续服务就不需要解析JWT令牌了，可以直接从请求的Header中获取到用户信息。
 * 将登录用户的JWT转化成用户信息的全局过滤器
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private static Logger logger = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            logger.info("======================>AuthGlobalFilter.filter() user:{}", userStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            logger.info(e.getMessage(), e);
        }
        return chain.filter(exchange);


        /*ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        logger.info("网关收到请求地址：" + request.getURI().toString());

        //当前请求的路径
        String url = request.getPath().toString();
        ServerHttpRequest.Builder mutate = request.mutate();
        ServerHttpRequest build = mutate.build();
        //从请求头获取toekn
        String accessToken = request.getHeaders().getFirst("Authorization");
        if (StrUtil.isEmpty(accessToken)) {
            return chain.filter(exchange);
        } else if (StringUtils.isNotBlank(accessToken)) {
            logger.info(accessToken);
            //已经登录的用户实例
            Principal principal = getUserInstence(accessToken);
            if (null != principal) {
                if (principal.getStat() == Principal.ABNORMAL_STAT.ACTIVE) {
                    return chain.filter(exchange.mutate().request(build).build());
                } else if (principal.getStat() == Principal.ABNORMAL_STAT.ELIMINATE) {
                    return getVoidMono(exchange, "系统检测到其他设备登录了您的账户，请重新登录，如果不是您本人操作，请尝试修改密码！", HttpStatus.UNAUTHORIZED);
                }
                return getVoidMono(exchange, "您登录已经过期，请重新登录！", HttpStatus.UNAUTHORIZED);
            }
            return getVoidMono(exchange, "您登录凭证无效，请重新登录！", HttpStatus.UNAUTHORIZED);
        }
        return getVoidMono(exchange, "您登录凭证无效，请重新登录！", HttpStatus.UNAUTHORIZED);*/
    }

    /*public Principal getUserInstence(String accessToken) {
        String s = redisTemplate.opsForValue().get(accessToken);
        return null==s?new Principal(Principal.ABNORMAL_STAT.EXPIRE,null):JsonUtil.parseObject(s, Principal.class);
    }*/
}
