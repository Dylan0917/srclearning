package com.yu.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 13:50
 */
public class SaveSession {
    private static final Map<String, Long> map1 = new HashMap<>();
    private SaveSession(){}
    private static class LaySaveSession{
        private static final SaveSession instance = new SaveSession();
    }

    public static SaveSession getInstance(){
        return LaySaveSession.instance;
    }
     //保存登录时间
    public void save(String phone, Long time) {
        synchronized (map1) {
            map1.put(phone, time);
        }
    }

}
