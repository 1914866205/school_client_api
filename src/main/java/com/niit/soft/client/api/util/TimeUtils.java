package com.niit.soft.client.api.util;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author wf
 * @Date 2020/6/9
 * @Version 1.0
 */
public class TimeUtils {

    public static int getExaminationStartStatus(Timestamp time) {
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime dataTime = time.toLocalDateTime();
        if (dataTime.getDayOfYear() == nowTime.getDayOfYear()) {
            return 0;
        }
        if (dataTime.getDayOfYear() > nowTime.getDayOfYear()) {
            return dataTime.getDayOfYear() - nowTime.getDayOfYear();
        }
        return -1;
    }
}
