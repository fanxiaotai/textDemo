package com.fanyitai.text.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    private Integer id;
    private Integer userId;
    private String roleName;
    @Transient
    private Integer life;  // '生命',
    private Integer lifeMax; // 最大生命
    @Transient
    private Integer magic; //魔法
    private Integer magicMax; //最大魔法
    private Integer attack;  // '攻击'
    private Integer roleLeave; //角色等级
    private Integer exp; //经验
    private Integer leaveExp; //升级所需经验
    private Integer freelyDistributable; //自由可分配的属性点
    private Integer gold; //金钱
    private Integer skillPoints; //技能点
    @Transient
    private Integer feedDegree; //饥饿度
    private Integer feedDegreeMax; //最大饥饿度
    private Integer defaultRole; //是否为默认角色，1是默认
    private String createTime; //创建时间
}
