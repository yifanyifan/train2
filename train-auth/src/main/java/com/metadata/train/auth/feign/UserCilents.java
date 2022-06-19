package com.metadata.train.auth.feign;

import com.metadata.train.entity.sys.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "train-sys")
public interface UserCilents {
    @RequestMapping(value = "/sys/user/loadByUsername", method = RequestMethod.GET)
    User loadUserByUsername(@RequestParam String username);
}
