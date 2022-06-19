package com.metadata.train.auth.controller;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Integer DATA_SIZE_64 = 64;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        //Redis
        //return authentication.getPrincipal();

        //JWT
        String header = request.getHeader("Authorization");
        String token = StrUtil.subAfter(header, "Bearer ", false);

        return Jwts.parser()
                .setSigningKey("asdqweasasdqweasasdqweasasdqweasasdqweasasdqweasasdqweasasdqweas".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
