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

    public static boolean roleLeave(Role roleByRoleId) {
        boolean isRoleLeave = false;
        Integer leaveExp = roleByRoleId.getLeaveExp();
        Integer exp = roleByRoleId.getExp();
        Integer roleLeave = roleByRoleId.getRoleLeave();
        if (exp>=leaveExp){
            exp -= leaveExp;
            roleLeave++;
            leaveExp = roleLeave*roleLeave*10;
            //设置升级后剩余点经验
            roleByRoleId.setExp(exp);
            //设置角色等级
            roleByRoleId.setRoleLeave(roleLeave);
            //设置下次升级点所需经验
            roleByRoleId.setLeaveExp(leaveExp);
            //升级后增加属性点,升级+2
            roleByRoleId.setFreelyDistributable(roleByRoleId.getFreelyDistributable()+2);
            //升级后增加技能点，升级+1
            roleByRoleId.setSkillPoints(roleByRoleId.getSkillPoints()+1);
            isRoleLeave=true;
        }
        return isRoleLeave;
    }

    public static boolean lifeMaxAdd(Role role) {
        boolean isRoleLeave = false;
        Integer freelyDistributable = role.getFreelyDistributable();
        if (freelyDistributable>=1){
            freelyDistributable--;
            role.setFreelyDistributable(freelyDistributable);
            role.setLifeMax(role.getLifeMax()+10);
            isRoleLeave = true;
        }
        return isRoleLeave;
    }

    public static boolean magicMaxAdd(Role role) {
        boolean isRoleLeave = false;
        Integer freelyDistributable = role.getFreelyDistributable();
        if (freelyDistributable>=1){
            freelyDistributable--;
            role.setFreelyDistributable(freelyDistributable);
            role.setMagicMax(role.getMagicMax()+10);
            isRoleLeave = true;
        }
        return isRoleLeave;
    }

    public static boolean attackAdd(Role role) {
        boolean isRoleLeave = false;
        Integer freelyDistributable = role.getFreelyDistributable();
        if (freelyDistributable>=1){
            freelyDistributable--;
            role.setFreelyDistributable(freelyDistributable);
            role.setAttack(role.getAttack()+1);
            isRoleLeave = true;
        }
        return isRoleLeave;
    }
}
