package com.metadata.train.api.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.metadata.train.api.service.GoodsService;
import com.metadata.train.api.vo.GoodsVO;
import com.metadata.train.api.vo.SignedParam;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {
    public static final Map<String, String> map = new HashMap<String, String>() {{
        put("iamappid", "iamkey");
    }};

    /**
     * 获取SecurityKey
     *
     * @param appId
     * @return
     */
    @Override
    public String getSecurityKey(String appId) {
        if (map.containsKey(appId)) {
            return map.get(appId);
        }
        return null;
    }

    /**
     * 权限验证
     *
     * @param goodsVO
     * @param signedParam
     * @return
     */
    @Override
    public Boolean sign(GoodsVO goodsVO, SignedParam signedParam) {
        // 权限鉴定
        // 可通过redis设置一层缓存
        // appId + timestamp ： sign : value
        // 拼装Map，可通过反射形式获取
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", goodsVO.getId());
        param.put("name", goodsVO.getName());
        param.put("appId", signedParam.getAppId());
        param.put("timestamp", signedParam.getTimestamp());
        param.put("nonce", signedParam.getNonce());

        SortedMap<String, Object> sortedMap = new TreeMap(param);
        StringBuffer plainText = new StringBuffer();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            plainText.append(entry.getKey() + "=" + entry.getValue());
            plainText.append("&");
        }
        plainText.deleteCharAt(plainText.length() - 1);
        plainText.append(GoodsServiceImpl.map.get(signedParam.getAppId()));
        System.out.println(plainText);

        String sign = MD5.create().digestHex(plainText.toString());
        System.out.println(sign);

        if (sign.equals(signedParam.getSign())) {
            return true;
        }
        return false;
    }

    /**
     * 获取列表
     *
     * @param goodsVO
     * @return
     */
    @Override
    public List<GoodsVO> goodsList(GoodsVO goodsVO) {
        List<GoodsVO> goodsVOList = new ArrayList<GoodsVO>();
        GoodsVO goodsVO1 = new GoodsVO();
        goodsVO1.setId("1");
        goodsVO1.setName("a");
        GoodsVO goodsVO2 = new GoodsVO();
        goodsVO2.setId("2");
        goodsVO2.setName("b");
        if("1".equals(goodsVO.getId())){
            goodsVOList.add(goodsVO1);
        }
        if("2".equals(goodsVO.getId())){
            goodsVOList.add(goodsVO2);
        }

        return goodsVOList;
    }
}
