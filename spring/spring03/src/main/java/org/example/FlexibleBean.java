package org.example;

import java.util.List;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/9/12 11:26
 */
public class FlexibleBean {
    private Object[] arr;
    private List list;
    private Map map;


    public Object[] getArr() {
        return arr;
    }

    public void setArr(Object[] arr) {
        this.arr = arr;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
