package com.dxx.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 单例获取applicationContext
 * @author cp5
 * @date 2020年 07月30日 11:50:39 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static SpringContextUtil springContextUtil;

    private SpringContextUtil(){}

    public static SpringContextUtil getInstance() {
        if(springContextUtil==null){
            springContextUtil=new SpringContextUtil();
        }

        return springContextUtil;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    /**
     * 读取spring配置参数，注意只能读取application的配置，不能读取bootstrap的配置
     * @param key
     * @return
     */
    public static String getConfigValue(String key){
        return SpringContextUtil.getInstance().getApplicationContext().getEnvironment().getProperty(key);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return SpringContextUtil.getInstance().getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(Class<T> t) {
        return SpringContextUtil.getInstance().getApplicationContext().getBean(t);
    }
}
