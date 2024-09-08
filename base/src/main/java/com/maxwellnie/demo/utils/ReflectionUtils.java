package com.maxwellnie.demo.utils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Maxwell Nie
 */
public class ReflectionUtils {
    /**
     * 通过反射创建一个对象。
     *
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T newInstance(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getConstructor().newInstance();
    }

}
