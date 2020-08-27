package com.cdfg.wxmall.dao;

import com.cdfg.wxmall.pojo.dto.CartDto;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface CartDao {

    public CartDto cart(String proId);
    
}
