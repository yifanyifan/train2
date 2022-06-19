package com.metadata.train.sys.feign;

import com.metadata.train.auth.Oauth2TokenDto;
import com.metadata.train.common.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "train-auth")
public interface AuthCilents {
    @RequestMapping(value = "/auth/oauth/token", method = RequestMethod.POST)
    ResultEntity<Oauth2TokenDto> getToken(@RequestParam Map<String, String> params);
}
