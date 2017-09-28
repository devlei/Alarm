package com.iss.phonealarm.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by weizhilei on 2017/9/25.
 */
public class CollectionUtils {

    public static boolean isEmpty(List<?> list) {
        return null == list || list.size() == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(String... array) {
        return null == array || array.length == 0;
    }

    public static <T> T[] convertListToArray(Class<T> type, List<T> list) {
        if (!isEmpty(list)) {
            T[] tArray = (T[]) Array.newInstance(type, list.size());
            list.toArray(tArray);
            return tArray;
        }
        return null;
    }

    public static <T> List<T> convertArrayToList(T[] array) {
        return Arrays.asList(array);
    }
}
