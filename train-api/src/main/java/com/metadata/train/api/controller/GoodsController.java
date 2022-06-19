package com.metadata.train.api.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.metadata.train.api.service.GoodsService;
import com.metadata.train.api.vo.GoodsVO;
import com.metadata.train.api.vo.SignedParam;
import com.metadata.train.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Test
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    public GoodsService goodsService;

    /**
     * 获取SecurityKey
     */
    @GetMapping("getSecurityKey")
    public ResultEntity getSecurityKey(String appId) throws Exception {
        String securityKey = goodsService.getSecurityKey(appId);
        if (StrUtil.isNotBlank(securityKey)) {
            return ResultEntity.success(securityKey);
        }
        return ResultEntity.failed("没有指定appId");
    }

    /**
     * 服务器
     */
    @PostMapping("/goodsList")
    public ResultEntity goodsList(@RequestBody GoodsVO goodsVO, SignedParam signedParam) throws Exception {
        //鉴权
        Boolean ok = goodsService.sign(goodsVO, signedParam);
        if (!ok) {
            return ResultEntity.validateFailed("权限验证失败");
        }

        // 查询列表
        List<GoodsVO> goodsVOList = goodsService.goodsList(goodsVO);
        return ResultEntity.success(goodsVOList);
    }

    /**
     * 模拟客户端加密过程
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //参数组合后排序
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", "1");
        param.put("name", "a");
        param.put("appId", "iamappid");
        param.put("timestamp", System.currentTimeMillis());
        param.put("nonce", new Random().nextInt(10000));

        SortedMap<String, Object> sortedMap = new TreeMap(param);
        StringBuffer plainText = new StringBuffer();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            plainText.append(entry.getKey() + "=" + entry.getValue());
            plainText.append("&");
        }
        plainText.deleteCharAt(plainText.length() - 1);
        plainText.append("iamkey");
        System.out.println(plainText);

        String sign = MD5.create().digestHex(plainText.toString());
        System.out.println(sign);
    }

}

