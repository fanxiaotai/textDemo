package com.fanyitai.text.demo.service.impl;

import com.fanyitai.text.demo.bean.Role;
import com.fanyitai.text.demo.bean.User;
import com.fanyitai.text.demo.mapper.RoleMapper;
import com.fanyitai.text.demo.service.RoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> getRoleByUserId(String userId) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("userId",userId);
        return roleMapper.selectByExample(example);
    }

    @Override
    public void insertRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public Role selectRoleByRoleName(String roleName) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("roleName",roleName);
        return roleMapper.selectOneByExample(example);
    }

    @Override
    public boolean checkRoleByRoleName(String roleName) {
        Role role = selectRoleByRoleName(roleName);
        return role != null;
    }

    @Override
    public Role checkRoleByUserId(String id, String roleId) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("roleId",roleId).andEqualTo("userId",id);
        return roleMapper.selectOneByExample(example);
    }

    @Override
    public void updateRole(Role newRole) {
        roleMapper.updateByPrimaryKey(newRole);
    }

    @Override
    public void deleteRoleByRoleId(String roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public Role getRoleByRoleIdAndUserId(String id, String roleId) {
        Role roleByRoleId = getRoleByRoleId(roleId);
        if (roleByRoleId!=null){
            if (id.equals(roleByRoleId.getId())){
                return roleByRoleId;
            }
        }
        return null;
    }

    @Override
    public Role getRoleByRoleId(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
