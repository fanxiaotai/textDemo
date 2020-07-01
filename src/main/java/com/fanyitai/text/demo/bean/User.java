package com.fanyitai.text.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Integer id;
    private String userName;
    private String userPassword;
    private String email;
    //注册时间
    private String registerTime;
    //上次登陆时间
    private String loginTime;
}
