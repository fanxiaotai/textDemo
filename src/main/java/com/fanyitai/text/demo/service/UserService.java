package com.fanyitai.text.demo.service;

import com.fanyitai.text.demo.bean.Role;
import com.fanyitai.text.demo.bean.User;
import com.fanyitai.text.demo.util.ResultEntity;

import java.util.List;

public interface UserService {

    User login(String username, String userPassword);

    ResultEntity<String> register(User user);

}
