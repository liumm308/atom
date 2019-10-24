package com.library.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.library.common.bean.UserInfoBean;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.LinkedHashMap;


@Service(value = "UserInfoUtils")
public class UserInfoUtils {

    public String getUserInfo(String jsonStr, HttpServletRequest request){

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONObject baseInfo = jsonObject.getJSONObject("baseInfo");

        HttpSession session = request.getSession();
        baseInfo.put("userId", session.getAttribute("userId"));
        return jsonObject.toJSONString();
    }

    public void getUserInfoBean(Principal principal, HttpServletRequest request){

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        LinkedHashMap map = (LinkedHashMap) oAuth2Authentication.getUserAuthentication().getDetails();
        LinkedHashMap linkedHashMap = (LinkedHashMap) map.get("principal");
        JSONObject json = JsonPluginsUtil.beanToJsonObj(linkedHashMap);
        UserInfoBean userInfoBean = JsonPluginsUtil.jsonToBean(json.toJSONString(),UserInfoBean.class);

        HttpSession session = request.getSession();
        session.setAttribute("userId", userInfoBean.getId());
        session.setAttribute("userName", userInfoBean.getUsername());

    }
}
