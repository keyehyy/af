package com.gt.common.constant;

import java.util.HashMap;
import java.util.Map;


public class Constant {
    public static Map<String, String> pdNum = new HashMap<String, String>() {
        {
            put("1", "A");//提讯
            put("2", "B");//律师
            put("3", "C");//提解
            put("4", "D");//家属送物
            put("5", "F");//家属会见
        }
    };
}
