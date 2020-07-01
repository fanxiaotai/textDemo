package com.fanyitai.text.demo.util;

import com.fanyitai.text.demo.bean.Role;
import com.fanyitai.text.demo.service.RoleService;

import java.util.List;

public class RoleUtils {

    /**
     * 创建新角色
     */
    public static Role creatNewRole(Integer userId,String roleName){
        Role role = new Role();
        role.setUserId(userId);
        role.setRoleName(roleName);
        role.setLifeMax(50);
        role.setMagicMax(50);
        role.setAttack(10);
        role.setRoleLeave(1);
        role.setExp(0);
        role.setLeaveExp(10);
        role.setFreelyDistributable(2);
        role.setGold(0);
        role.setSkillPoints(1);
        role.setFeedDegreeMax(100);
        role.setDefaultRole(0);
        role.setCreateTime(TimeUtils.getNowStringTime());
        return role;
    }

    /**
     * 检查用户是否拥有该角色
     */
    public static boolean checkUserRole(List<Role> roleList,String RoleId){
        boolean isUserRole = false;
        int RoleIdInt = Integer.parseInt(RoleId);
        for (Role role : roleList) {
            if (role.getId().equals(RoleIdInt)){
                isUserRole = true;
            }
        }
        return isUserRole;
    }
}
