package com.fanyitai.text.demo.controller;

import com.fanyitai.text.demo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: fanyitai
 * @Date: 2019/12/9 19:33
 * @Version 1.0
 */
@Controller
public class RedirectController {


    /**
     * 跳转到用户密码登陆页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(String ReturnUrl, ModelMap modelMap){
        modelMap.put("ReturnUrl",ReturnUrl);
        return "login";
    }


    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/toReg")
    public String toReg(){
        return "reg";
    }

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping("/index.html")
    public String index(ModelMap modelMap, HttpSession session){
        User userInfo = (User) session.getAttribute("userInfo");
        if (userInfo!=null){
            modelMap.put("userId",userInfo.getId());
            modelMap.put("userName",userInfo.getUserName());
        }
        return "index";
    }

}
