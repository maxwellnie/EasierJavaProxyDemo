package com.maxwellnie.demo.factory;


import com.maxwellnie.demo.info.impl.ProxyInfo;

/**
 * <p>代理对象工厂<p/>
 * <p>Proxy object factory<p/>
 * @author Maxwell Nie
 */
public interface ProxyObjectFactory<T>{
    /**
     * <p>生产代理对象<p/>
     * <p>Produce proxy object<p/>
     * @param target 目标对象
     * @param proxyInfo 代理信息
     * @return proxy object 代理对象
     */
    T produce(Object target, ProxyInfo proxyInfo);

}
