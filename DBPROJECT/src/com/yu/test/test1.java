package com.yu.test;


import com.yu.core.text.StrFormatter;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/6/25 12:13
 */
public class test1 {
    public static void main(String[] args) {
        //字符串替换
        System.out.println(StrFormatter.format("this is \\{}  for {}", "a", "b"));
        System.out.println(StrFormatter.format("\"this {}  for {} gfgfg", "a", "b"));
        format("this is {}  for {} gfgf","1","2");
    }
    //字符串替换
    public static void format(String str,Object ... params){
        int strLen = str.length();
        StringBuilder builder = new StringBuilder(strLen + 50);
        int handledPosition = 0;//记录已经处理到的位置
        int delimIndex; //占位符位置
        for (int argIndex = 0; argIndex < params.length; argIndex++) {
            delimIndex = str.indexOf("{}",handledPosition);
            if (delimIndex == -1){
                if (handledPosition == 0){
                    System.out.println(str);
                }
                builder.append(str,handledPosition,delimIndex-1);
                System.out.println(builder.toString());
            }
            builder.append(str,handledPosition,delimIndex);
            builder.append(params[argIndex]);
            handledPosition = delimIndex + 2;
        }
        // 加入最后一个占位符后所有的字符
        builder.append(str, handledPosition, str.length());
        System.out.println(builder.toString());
    }
}
