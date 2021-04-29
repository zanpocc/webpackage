package com.zanpo.it.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用JSON进行普通对象和集合对象的深拷贝
 *
 * @author cg
 * @date 2020/11/1 21:00
 */
@SuppressWarnings("unchecked")
public final class CopyUtils {

    private static final Gson gson = new Gson();

    public static final <T> T copy(Object source,Class<T> target) {
        String s = gson.toJson(source);
        T o = gson.fromJson(s, target);
        return o;
    }

    public static final <T> List<T> copyList(List source, Class<T> target) {
        int size = source.size();
        List result = new ArrayList(size);
        for(Object o : source){
            result.add(copy(o,target));
        }
        return result;
    }

}
