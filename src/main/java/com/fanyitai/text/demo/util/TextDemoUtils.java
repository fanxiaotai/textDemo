package com.fanyitai.text.demo.util;

import com.fanyitai.text.demo.bean.GameProp;
import com.fanyitai.text.demo.bean.Monster;
import com.fanyitai.text.demo.bean.StoryCard;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TextDemoUtils{

    /**
     * 参数校验
     */
    public static boolean checkBean(Object ...o){
        for (Object o1 : o) {
            if (o1==null) return false;
        }
        return true;
    }

    /**
     * 字符串空值校验
     */
    public static boolean checkString(String ...s){
        for (String s1 : s) {
            if (StringUtils.isBlank(s1)) return false;
        }
        return true;
    }


    /**
     * 读取properties文件
     * @param textName
     * @return
     */
    public static Properties getProperties(String textName){
        InputStream resourceAsStream = TextDemoUtils.class.getClassLoader().getResourceAsStream(textName);
        if (resourceAsStream==null){
            return null;
        }
        Properties props = new Properties();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(resourceAsStream));
            props.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    /**
     * 将Properties转化为StoryCard
     */
    public static StoryCard getProperties2StoryCard(Properties properties,StoryCard o){
        if (properties==null){
            return null;
        }
        //buttonMap
        Map<String, String> map = new HashMap<>();
        //循环StoryCard的全部属性并赋值
        for (Field declaredField : o.getClass().getDeclaredFields()) {
            String name = declaredField.getName();
            declaredField.setAccessible(true);
            try {
                Object o1 = properties.get(name);
                if (o1!=null){
                    //buttons写法规则
                    if ("buttons".equals(name)){
                        String buttons = (String) o1;
                        String[] split = buttons.split("；");
                        for (String button : split) {
                            String[] split1 = button.split("，");
                            if (split1.length!=2){
                                throw new RuntimeException("发现不正常的后续节点，请检查");
                            }
                            map.put(split1[0],split1[1]);
                        }
                    }else {
                        declaredField.set(o,o1);
                    }
                }
            } catch (IllegalAccessException e) {
                LoggerFactory.getLogger(o.getClass()).error("加载失败"+ExceptionLogUtils.E2String(e));
            }
        }
        try {
            o.getClass().getDeclaredField("buttonMap").set(o,map);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LoggerFactory.getLogger(o.getClass()).error("加载bean失败"+ExceptionLogUtils.E2String(e));
        }
        return o;
    }

    /**
     * 将Properties转化为Monster
     */
    public static Monster getProperties2Monster(Properties properties, Monster o){
        if (properties==null){
            return null;
        }
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            declaredField.setAccessible(true);
            try {
                Object o1 = properties.get(name);
                if (o1!=null){
                    declaredField.set(o,o1);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    /**
     * 将Properties转化为GameProp
     */
    public static GameProp getProperties2GameProp(Properties properties, GameProp o){
        if (properties==null){
            return null;
        }
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            declaredField.setAccessible(true);
            try {
                Object o1 = properties.get(name);
                if (o1!=null){
                    declaredField.set(o,o1);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}
