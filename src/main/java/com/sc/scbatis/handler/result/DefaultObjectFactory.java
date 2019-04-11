package com.sc.scbatis.handler.result;

/**
 * @author: shichao
 * @date: 2019/4/11
 * @description:
 */
public class DefaultObjectFactory {
    /**
     * 通过传进来的类型，通过反射生成对象
     *
     * @param clazz
     * @return
     */
    public Object createOriginalObject(Class clazz) throws IllegalAccessException, InstantiationException {
        if (null == clazz) {
            return null;
        }
        return clazz.newInstance();
    }
}
