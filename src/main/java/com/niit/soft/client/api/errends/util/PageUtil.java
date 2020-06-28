package com.niit.soft.client.api.errends.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wl
 * @ClassNamePageUtil
 * @Description TODO
 * @Date 2020/6/16
 * @Version 1.0
 */
@Slf4j
public class PageUtil {
    public static  <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();

        log.info(String.valueOf(start));
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        log.info(String.valueOf(end));
        if (start > end) {
            start = end;
        }
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }
}
