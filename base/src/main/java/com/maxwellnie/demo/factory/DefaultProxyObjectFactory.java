package com.maxwellnie.demo.factory;


import com.maxwellnie.demo.exception.ProxyExtendsException;
import com.maxwellnie.demo.info.MethodProxyInfo;
import com.maxwellnie.demo.info.impl.ProxyInfo;
import com.maxwellnie.demo.utils.StringUtils;

import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>代理对象工厂<p/>
 * <p>Proxy object factory<p/>
 * @author Maxwell Nie
 */
public class DefaultProxyObjectFactory implements ProxyObjectFactory<Object> {
    @Override
    public Object produce(Object target, ProxyInfo proxyInfo) {
        Class<?> [] interfaceClasses = proxyInfo.getInterfaces();
        MethodProxyInfo[] methodProxyInfos = proxyInfo.getMethodProxyInfos();
        if (interfaceClasses == null)
            throw new ProxyExtendsException("interfaceClass is null");
        if (methodProxyInfos.length == 0) {
            return target;
        }
        checkInterfaces(interfaceClasses);
        Map<String, MethodProxyInfo> proxyInfoMap = new LinkedHashMap<>();
        for (MethodProxyInfo methodProxyInfo : methodProxyInfos) {
            proxyInfoMap.put(StringUtils.getMethodDeclaredName(methodProxyInfo.getMethodName(), methodProxyInfo.getParameterTypes()), methodProxyInfo);
        }
        /**
         * <p>创建代理对象</p>
         * <p>Create proxy object</p>
         */
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                interfaceClasses,
                new SimpleProxy(proxyInfoMap, target));
    }
    /**
     * <p>检查代理接口是否为空，是否为接口</p>
     * <p>Check whether the proxy interface is empty and whether it is an interface</p>
     */
    private void checkInterfaces(Class<?>[] interfaceClasses) throws ProxyExtendsException{
        for (Class<?> interfaceClass : interfaceClasses){
            if(interfaceClass == null)
                throw new ProxyExtendsException("interfaceClass is null");
            if (!interfaceClass.isInterface())
                throw new ProxyExtendsException(interfaceClass.getName() + " is not interface");
        }
    }
}

