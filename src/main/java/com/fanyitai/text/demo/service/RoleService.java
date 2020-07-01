package com.fanyitai.text.demo.service;

import com.fanyitai.text.demo.bean.Role;
import com.fanyitai.text.demo.bean.User;

import java.util.List;

public interface RoleService {

    List<Role> getRoleByUserId(String userId);

    void insertRole(Role role);

    Role selectRoleByRoleName(String roleName);

    boolean checkRoleByRoleName(String roleName);

    Role checkRoleByUserId(String userId, String roleId);

    void updateRole(Role newRole);

    void deleteRoleByRoleId(String roleId);

    Role getRoleByRoleIdAndUserId(String userId, String roleId);

    Role getRoleByRoleId(String roleId);
}
