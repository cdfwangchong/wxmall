package com.cdfg.wxmall.controller;

import cn.cdfg.exceptionHandle.ExceptionPrintMessage;
import com.alibaba.fastjson.JSONObject;
import com.cdfg.wxmall.pojo.dto.Code;
import com.cdfg.wxmall.pojo.dto.OpenId;
import com.cdfg.wxmall.pojo.dto.RegUser;
import com.cdfg.wxmall.pojo.utill.AuthUtil;
import com.cdfg.wxmall.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cdfg")
public class UserController {

    @Autowired
    private UserService service=null;
    Logger logger = Logger.getLogger(UserController.class);
    //注册
    @RequestMapping(value={"/getregister"}, method = RequestMethod.POST)
    @ResponseBody
    public String cdfgdeposit(@RequestBody RegUser reguser) {

        String openId = reguser.getOpenId();
        String nickName = reguser.getNickName();
        String gender = reguser.getGender();
        String language = reguser.getLanguage();
        String province = reguser.getProvince();
        String city = reguser.getCity();
        String country = reguser.getCountry();
        String avatarUrl = reguser.getAvatarUrl();

        logger.info("取到注册用户信息"+openId);

        Map<String,String> param=new HashMap<String,String>();
        param.put("openId", openId);
        param.put("nickName", nickName);
        param.put("iSex", gender);
        param.put("avatarUrl", avatarUrl);

        JSONObject json = new JSONObject();

        try {
            service.getCoupon(param);

        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取注册用户信息存储过程的返回值异常");
            json.put("ret_flag", "-1");
            return json.toString();
        }

        json.put("ret_flag", param.get("ret_flag"));
        //写入日志
        logger.info("ret_flag（返回标志）:"+ param.get("ret_flag"));
        return json.toString();
    }

    //登录
    @RequestMapping(value={"/getlogin"}, method = RequestMethod.POST,produces="json/html; charset=UTF-8")
    @ResponseBody
    public String login(@RequestBody OpenId open) {

        String openId = open.getOpenId();

        logger.info("取到登录用户信息"+openId);

        Map<String,String> param=new HashMap<String,String>();
        param.put("open_id", openId);

        String nickName = null;
        String avatarUrl = null;
        String ret_flag = null;

        JSONObject json = new JSONObject();

        try {
            service.login(param);
            //获得返回值
            ret_flag=param.get("ret_flag");
            nickName=param.get("nickName");
            avatarUrl=param.get("avatarUrl");

            if (!ret_flag.equals("1001")) {
                json.put("ret_flag", ret_flag);
                json.put("avatarUrl", "");
                json.put("nickName","");

            }else {
                json.put("ret_flag", ret_flag);
                json.put("nickName", nickName);
                json.put("avatarUrl",avatarUrl);
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("取到登录用户信息存储过程的返回值异常");
            json.put("ret_flag", "1003");
            json.put("nickName ", "");
            json.put("avatarUrl","");
            return json.toString();
        }

        logger.info("获取登录信息返回标志："+ret_flag+"昵称："+nickName);
        return json.toString();
    }

    @RequestMapping(value={"/getopenid"}, method = RequestMethod.POST)
    @ResponseBody
    public String wechatopenid(@RequestBody Code verCode) {

        String code = verCode.getVerCode();
        logger.info("取到code"+code);

        JSONObject jsonObject = new JSONObject();
        JSONObject json = new JSONObject();
        try{
            jsonObject = AuthUtil.doGetJson(code);

            //从返回的JSON数据中取出access_token和openid，拉取用户信息时用
            if (jsonObject.containsKey("access_token")) {
                String token =  jsonObject.getString("access_token");
                String openId = jsonObject.getString("openid");

                logger.info("token:"+token+";"+"openId:"+openId);

                //写入日志
                logger.info("ret_result（返回结果）:"+openId);

                json.put("ret_flag","2001");//获取openid成功
                json.put("openId",openId);

            }else {
                String errcode =  jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");

                logger.info("errcode:"+errcode+";"+"errmsg:"+errmsg);
                json.put("ret_flag","2002");//获取openid失败
                json.put("openId","");

                logger.info("ret_result（返回结果）:"+"errcode:"+errcode+"errmsg:"+errmsg);
                return json.toString();
            }
        } catch (Exception e) {
            logger.error(new ExceptionPrintMessage().errorTrackSpace(e));
            logger.error("获取openid存储过程的返回值异常");
            json.put("ret_flag","2003");//获取存储过程的返回值异常
            json.put("openId","");
            return json.toString();
        }
        return json.toString();
    }
}
