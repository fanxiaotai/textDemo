package com.fanyitai.text.demo.controller.login;

import com.fanyitai.text.demo.annotation.Login;
import com.fanyitai.text.demo.bean.Role;
import com.fanyitai.text.demo.bean.User;
import com.fanyitai.text.demo.service.RoleService;
import com.fanyitai.text.demo.util.ResultEntity;
import com.fanyitai.text.demo.util.RoleUtils;
import com.fanyitai.text.demo.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Resource
    RoleService roleService;

    @RequestMapping("/toUser")
    @Login
    public String toUser(HttpSession session, HttpServletResponse response,ModelMap modelMap) throws IOException {
        //获取用户信息
        User user = (User)session.getAttribute("userInfo");
        if (user==null){
            response.sendRedirect("/toLogin");
            return "user";
        }
        //根据用户信息获取用户的所有角色
        List<Role> roleList = roleService.getRoleByUserId(user.getId());
        modelMap.put("roleList",roleList);

        //将角色信息返回给前台页面
        return "user";
    }

    /**
     * 根据角色id查询用户的该角色
     */
    @RequestMapping("/getRolesByRoleId")
    @ResponseBody
    @Login
    public ResultEntity<String> getRolesByRoleId(HttpSession session,String roleId){
        User user = (User)session.getAttribute("userInfo");
        if (user==null){
            return ResultEntity.failed("请先登陆");
        }
        Role role = roleService.getRoleByRoleIdAndUserId(user.getId(),roleId);
        if (role==null){
            return ResultEntity.failed("你未拥有该角色");
        }
        return ResultEntity.successWithData(role);
    }

    /**
     * 根据用户id获取用户的所有角色的信息
     */
    @RequestMapping("/getRolesByUserId")
    @ResponseBody
    @Login
    public ResultEntity<String> getRolesByUserId(HttpSession session){
        //获取用户信息
        User user = (User)session.getAttribute("userInfo");
        if (user==null){
            return ResultEntity.failed("请先登陆");
        }
        //根据用户信息获取用户的所有角色
        List<Role> roleList = roleService.getRoleByUserId(user.getId());

        //将角色信息返回
        return ResultEntity.successWithData(roleList);
    }

    /**
     * 删除角色
     */
    @RequestMapping("/deleteRoleByRoleId")
    @ResponseBody
    @Login
    public ResultEntity<String> deleteRoleByRoleId(HttpSession session,String roleId){
        //获取用户信息
        User user = (User)session.getAttribute("userInfo");
        if (user==null){
            return ResultEntity.failed("请先登陆");
        }
        if (roleService.getRoleByRoleIdAndUserId(user.getId(),roleId)==null){
            return ResultEntity.failed("你未拥有该角色");
        }
        roleService.deleteRoleByRoleId(roleId);
        return ResultEntity.successWithData("删除成功");
    }

    /**
     * 设置/更新默认角色
     */
    @RequestMapping("/updateDefaultRole")
    @ResponseBody
    @Login
    public ResultEntity<String> updateDefaultRole(HttpSession session,String roleId){
        //获取用户信息
        User user = (User)session.getAttribute("userInfo");
        if (user==null){
            return ResultEntity.failed("请先登陆");
        }
        //根据用户信息获取用户的所有角色
        List<Role> roleByRoleId = roleService.getRoleByUserId(user.getId());

        boolean isUserRole = false;
        Role defaultRole = null;
        Role newRole = null;

        Integer roleIdInt = Integer.parseInt(roleId);
        //判断用户是否拥有该角色
        for (Role role : roleByRoleId) {
            if (role.getId().equals(roleIdInt)){
                isUserRole=true;
                role.setDefaultRole(1);
                newRole = role;
            }
            //找出之前的默认角色
            if (role.getDefaultRole()==1){
                //将之前的默认角色取消
                role.setDefaultRole(0);
                defaultRole = role;
            }
        }

        if (!isUserRole){
            return ResultEntity.failed("你未拥有该角色");
        }

        roleService.updateRole(defaultRole);
        roleService.updateRole(newRole);
        return ResultEntity.successWithData(roleByRoleId);
    }

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("/insertNewRole")
    @ResponseBody
    @Login
    public ResultEntity<String> insertNewRole(HttpSession session,String roleName){
        //获取用户信息
        User user = (User)session.getAttribute("userInfo");
        if (user==null){
            return ResultEntity.failed("请先登陆");
        }
        if (StringUtils.isBlank(roleName)){
            return ResultEntity.failed("非法参数");
        }
        //检查该名称的角色是否存在
        if (roleService.checkRoleByRoleName(roleName)){
            return ResultEntity.failed("该角色名称已存在");
        }
        //创建新角色
        Role role = RoleUtils.creatNewRole(user.getId(), roleName);
        roleService.insertRole(role);
        return ResultEntity.successWithData("创建成功");
    }

}