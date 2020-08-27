package com.cdfg.wxmall.service;

import java.util.Map;

public interface UserService {
    public Map<String, String> getCoupon(Map<String, String> param);

    public Map<String, String> login(Map<String, String> param);

}
