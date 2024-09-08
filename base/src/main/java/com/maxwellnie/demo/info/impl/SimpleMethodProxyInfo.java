package com.maxwellnie.demo.info.impl;


import com.maxwellnie.demo.info.MethodProxyInfo;

import java.lang.reflect.Method;

/**
 * <p>被代理方法的特征</p>
 * <p>Characteristics of the agent method</p>
 * @author Maxwell Nie
 */
public class SimpleMethodProxyInfo implements MethodProxyInfo {
    private Method proxyMethod;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Class<?> returnType;
    private Object proxyObject;

    @Override
    public Object getProxyObject() {
        return proxyObject;
    }

    @Override
    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }
    @Override
    public Method getProxyMethod() {
        return proxyMethod;
    }

    @Override
    public void setProxyMethod(Method proxyMethod) {
        this.proxyMethod = proxyMethod;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public Class<?> getReturnType() {
        return returnType;
    }

    @Override
    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
