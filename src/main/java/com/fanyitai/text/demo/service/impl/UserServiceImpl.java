package com.fanyitai.text.demo.service.impl;

import com.fanyitai.text.demo.bean.Role;
import com.fanyitai.text.demo.bean.User;
import com.fanyitai.text.demo.mapper.UserMapper;
import com.fanyitai.text.demo.service.UserService;
import com.fanyitai.text.demo.util.ResultEntity;
import com.fanyitai.text.demo.util.TimeUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User login(String username, String userPassword) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",username)
                .andEqualTo("userPassword",userPassword);
        User user = userMapper.selectOneByExample(example);
        if (user==null){
            return null;
        }
        user.setLoginTime(TimeUtils.getNowStringTime());
        return user;
    }

    @Override
    public ResultEntity<String> register(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",user.getUserName());
        User user1 = userMapper.selectOneByExample(user);
        if (user1!=null){
            return ResultEntity.failed("用户名已存在");
        }
        user.setRegisterTime(TimeUtils.getNowStringTime());
        userMapper.insert(user);
        return ResultEntity.successNoData();
    }
}
