package com.cdfg.wxmall.controller;

import com.cdfg.wxmall.pojo.dto.CartDto;
import com.cdfg.wxmall.pojo.dto.GoodsList;
import com.cdfg.wxmall.pojo.utill.GoodsUtil;
import com.cdfg.wxmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cdfg")
public class CartController {
    @Autowired
    private CartService service;

    @RequestMapping(value = {"/queryCartGoodsPrice"},method = RequestMethod.POST)
    @ResponseBody
    public long queryCartGoodsPrice(@RequestBody GoodsList goods) {

        long proPricetotal = 0;
        long proAmounttotal = 0;
        long proCounttotal = 0;
        List<GoodsUtil> gulist = new ArrayList<GoodsUtil>();

        gulist = goods.getGoodslist();

        for (int i = 0; i < gulist.size(); i++) {
            GoodsUtil gu = gulist.get(i);
            String proId = gu.getProId();
            int count = gu.getCount();
            System.out.println(proId);
            CartDto cartdto = service.Cart(proId);

            long proPrice = cartdto.getProPrice() * count;
            long proAmount = cartdto.getProAmount() * count;

            proPricetotal = proPricetotal + proPrice;
            proAmounttotal = proAmounttotal + proAmount;
            proCounttotal = proCounttotal+(proPrice - proAmount);
        }
        return proPricetotal;
    }
}
