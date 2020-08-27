package com.cdfg.wxmall.service.Impl;

import com.cdfg.wxmall.dao.CartDao;
import com.cdfg.wxmall.pojo.dto.CartDto;
import com.cdfg.wxmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartdao = null;

    @Override
    public CartDto Cart(String proId) {
        return cartdao.cart(proId);
    }
}
