package com.fanyitai.text.demo.controller;

import com.fanyitai.text.demo.bean.User;
import com.fanyitai.text.demo.service.UserService;
import com.fanyitai.text.demo.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class loginController {

    private static final Logger logger = LoggerFactory.getLogger(loginController.class);

    @Resource
    UserService userService;

    /**
     * 登陆
     * @param session
     * @param username
     * @param userPassword
     * @return
     */
    @RequestMapping("/login")
    public ResultEntity<String> login(HttpSession session, String username, String userPassword){
        User user = userService.login(username,userPassword);
        if (user==null){
            return ResultEntity.failed("用户名或密码错误");
        }
        session.setAttribute("userInfo",user);
        return ResultEntity.successWithData("登陆成功");
    }

    /**
     * 注册
     */
    @RequestMapping("/reg")
    @ResponseBody
    public ResultEntity<String> reg(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 注销
     */
    @RequestMapping("/exit")
    @ResponseBody
    public void exit(HttpSession session){
        session.removeAttribute("userInfo");
    }
}
