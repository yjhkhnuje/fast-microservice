package com.lzm.fast.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 *
 * @author: ZhongMing.Liu
 * @create: 2020/4/22 14:25
 */
@Service
@Lazy(false)
@Slf4j
public class SpringUtil implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;


    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static String getRootRealPath() {
        String rootRealPath = "";
        try {
            rootRealPath = getApplicationContext().getResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            log.warn("获取系统根目录失败");
        }
        return rootRealPath;
    }

    public static String getResourceRootRealPath() {
        String rootRealPath = "";
        try {
            rootRealPath = new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            log.warn("获取资源根目录失败");
        }
        return rootRealPath;
    }


    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        log.info("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        if (SpringUtil.applicationContext != null) {
            log.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringUtil.applicationContext);
        }
        // NOSONAR
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringUtil.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Assert.isNull(applicationContext, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }


    public static String[] getBeanNamesForAnnotation(Class<? extends Annotation> clazz) {
        return getApplicationContext().getBeanNamesForAnnotation(clazz);
    }

    /**
     * 通过接口返回
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Collection<T> getBeansOfType(Class<T> tClass) {
        Map<String, T> map = applicationContext.getBeansOfType(tClass);
        if (map != null) {
            return map.values();
        }
        return null;
    }
}