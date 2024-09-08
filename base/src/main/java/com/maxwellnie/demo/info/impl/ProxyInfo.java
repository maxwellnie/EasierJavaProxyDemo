package com.maxwellnie.demo.info.impl;


import com.maxwellnie.demo.info.MethodProxyInfo;

/**
 * <p>代理信息</p>
 * <p>proxy information</p>
 * @author Maxwell Nie
 */
public class ProxyInfo {
    private MethodProxyInfo[] methodProxyInfos;
    /**
     * 代理类
     */
    private Class<?> clazz;
    /**
     * 代理对象
     */
    private Object proxyObject;
    /**
     * 被代理接口
     */
    private Class<?>[] interfaces;
    public MethodProxyInfo[] getMethodProxyInfos() {
        return methodProxyInfos;
    }

    public void setMethodProxyInfos(MethodProxyInfo[] methodProxyInfos) {
        this.methodProxyInfos = methodProxyInfos;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?>[] interfaces) {
        this.interfaces = interfaces;
    }
}
