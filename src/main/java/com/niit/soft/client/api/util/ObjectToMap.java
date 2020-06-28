package com.niit.soft.client.api.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tao
 * @version 1.0
 * @ClassName objectToMap
 * @Description TODO
 * @date 2020-05-30 20:58
 **/
public class ObjectToMap {
//    public static Map<?, ?> objectToMap(Object obj) {
//        if (obj == null) {
//            return null;
//        }
//        return new BeanMap(obj);
//    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }


}
