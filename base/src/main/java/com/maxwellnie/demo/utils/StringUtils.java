package com.maxwellnie.demo.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Maxwell Nie
 */
public class StringUtils {
    /**
     * 转换Method对象为可被处理的方法名
     *
     * @param method
     * @return
     */
    public static String getMethodDeclaredName(Method method) {
        return String.valueOf(Objects.hash(method.getName(), Arrays.hashCode(method.getParameterTypes())));
    }

    /**
     * 转换Method对象的信息为可被处理的方法名
     *
     * @param method
     * @param argsClass
     * @return
     */
    public static String getMethodDeclaredName(String method, Class<?>[] argsClass) {
        return String.valueOf(Objects.hash(method, Arrays.hashCode(argsClass)));
    }
}
