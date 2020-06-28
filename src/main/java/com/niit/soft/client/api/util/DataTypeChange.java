package com.niit.soft.client.api.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/5/30
 * @Version 1.0
 */
public class DataTypeChange {

    //object转换为map
    public static List<Map<String, Object>> objectToMap(Object[] objects) {
        if (objects == null) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Object obj : objects) {
            try {
                Map<String, Object> map = new LinkedHashMap<>();
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field field : fields) {
                    //设置私有属性为true
                    field.setAccessible(true);
                    //获取字段名和字段值
                    map.put(field.getName(), field.get(obj));
                }
                maps.add(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maps;
    }
}
