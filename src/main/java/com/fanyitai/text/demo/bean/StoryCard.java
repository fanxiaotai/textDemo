package com.fanyitai.text.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 故事卡片，记载着事件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryCard {

    private String id;
    private String storyDetails;//故事详情
    private String storyCardNextId;//下一节点的id们
    private Map<String,StoryCard> storyCardNext;//下一节点
    private String buttons;//可触发的按钮显示
    private Map<String,String> buttonMap;//k:按钮文本，v:下一节点的id
    private boolean isMonster;//是否为怪物
    private List<Monster>[] monsters;//存储怪物;
    private boolean isGameProp;//是否为道具
    private List<GameProp>[] gameProps;//存储道具
    private boolean isExplore;//是否为探索
    private String goodId;//好的概率触发
    private String badId;//坏的概率触发
    private List<String>[] goodIds;//好的id集合
    private List<String>[] badIds;//坏的id集合
}
