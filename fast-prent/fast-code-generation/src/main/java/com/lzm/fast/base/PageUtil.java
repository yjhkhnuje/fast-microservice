package com.lzm.fast.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @description:
 * @author: ZhongMing.Liu
 * @create: 2020/4/27 17:54
 */
public final class PageUtil {
    private PageUtil() {
    }

    /**
     * 分页默认current
     */
    private final static Integer PAGE_DEFAULT_CURRENT = 1;
    /**
     * 分页默认size
     */
    private final static Integer PAGE_DEFAULT_SIZE = 20;

    /**
     * 获取默认分页
     *
     * @param current
     * @param size
     * @param <T>
     * @return
     */
    public static <T> Page<T> getPage(Integer current, Integer size, boolean isSearchCount) {
        if (current == null || current <= 0) {
            current = PAGE_DEFAULT_CURRENT;
        }
        if (size == null || size <= 0) {
            size = PAGE_DEFAULT_SIZE;
        }
        return new Page(current, size, isSearchCount);
    }
}
