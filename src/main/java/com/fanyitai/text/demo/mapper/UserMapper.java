package com.fanyitai.text.demo.mapper;

import com.fanyitai.text.demo.bean.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}
