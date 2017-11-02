package com.csmall.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchao on 2015/10/8.
 */
public class CollectionHelper {
    public static List mapToList(Map map){
        List list = new ArrayList();
        if(map == null){
            return list;
        }
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = iterator.next();
            list.add(entry.getValue());
        }
        return list;
    }
}
