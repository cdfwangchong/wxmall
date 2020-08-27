package com.cdfg.wxmall.service.Impl;

import com.cdfg.wxmall.dao.UserDao;
import com.cdfg.wxmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao = null;
    @Override
    public Map<String, String> getCoupon(Map<String, String> param) {
        return userdao.registerUser(param);
    }
    @Override
    public Map<String, String> login(Map<String, String> param) {
        return userdao.login(param);
    }
}
