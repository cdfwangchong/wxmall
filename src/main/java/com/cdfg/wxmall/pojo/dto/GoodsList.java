package com.cdfg.wxmall.pojo.dto;

import com.cdfg.wxmall.pojo.utill.GoodsUtil;

import java.util.List;

public class GoodsList {
    public List<GoodsUtil> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(List<GoodsUtil> goodslist) {
        this.goodslist = goodslist;
    }

    List<GoodsUtil> goodslist;

}
