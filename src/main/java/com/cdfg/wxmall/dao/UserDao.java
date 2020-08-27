package com.cdfg.wxmall.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {
    public Map<String, String> registerUser(Map<String, String> param);

    public Map login(Map<String, String> param);

}
