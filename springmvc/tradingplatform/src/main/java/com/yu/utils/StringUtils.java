package com.yu.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/16 14:32
 */
public class StringUtils {
    //静态内部类单例
    private static class  LayHolder{
        private static final StringUtils instance = new StringUtils();
    }
    private StringUtils(){}
    public static StringUtils getInstance(){
        return LayHolder.instance;
    }

    public boolean isNullOrEmpty(Object obj){
        if (obj == null){
            return true;
        }
        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;
        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();
        if (obj instanceof Map)
            return ((Map) obj).isEmpty();
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }





}
