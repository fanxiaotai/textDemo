package com.fanyitai.text.demo.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * @Author: fanyitai
 * @Date: 2019/12/30 19:58
 * @Version 1.0
 */
@Data
public class Monster implements Cloneable, Serializable {

    private String id;
    private String nickname; // '怪物名称',
    public String monsterDescribe; //怪物描述
    private Integer attack;  // '攻击',
    public Integer life;  // '生命',
    private Integer monsterExp; //击败后可获得经验
    private Integer monsterGold; //击败后可获得金币
    private String gamePropId;//掉落道具的id
    private List<GameProp>[] gameProps;//掉落的道具集合
    private String nextId;//进阶怪物的id
    private int number;//战斗次数
    private int numberMax;//战斗次数最大值后进阶
    private Monster monsterNext;//进阶的怪物
}
