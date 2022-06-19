package com.metadata.train.api.service;

import com.metadata.train.api.vo.GoodsVO;
import com.metadata.train.api.vo.SignedParam;

import java.util.List;

public interface GoodsService {
    String getSecurityKey(String appId);

    Boolean sign(GoodsVO goodsVO, SignedParam signedParam);

    List<GoodsVO> goodsList(GoodsVO goodsVO);
}
