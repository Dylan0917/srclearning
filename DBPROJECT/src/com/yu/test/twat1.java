package com.yu.test;


import com.yu.core.text.StrFormatter;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/6/25 12:13
 */
public class twat1 {
    public static void main(String[] args) {
        //字符串替换
        System.out.println(StrFormatter.format("this is \\{}  for {}", "a", "b"));
        System.out.println(StrFormatter.format("\"this {}  for {}", "a", "b"));

    }
}
