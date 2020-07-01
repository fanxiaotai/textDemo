package com.fanyitai.text.demo.controller.login;

import com.fanyitai.text.demo.annotation.Login;
import com.fanyitai.text.demo.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class GameController {

    @RequestMapping("/toGame")
    @Login
    public String toGame(HttpSession session, HttpServletResponse response,ModelMap modelMap) throws IOException {
        //获取登陆信息
        User user = (User)session.getAttribute("session");
        if (user==null){
            response.sendRedirect("/toLogin");
        }
        //获取游戏角色
        //加载技能
        //将角色信息和技能传入前台
        //准备剧本
        return "game";
    }
}
