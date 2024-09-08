package com.maxwellnie.demo.parser;


import com.maxwellnie.demo.annoation.MethodInterceptor;
import com.maxwellnie.demo.annoation.ProxyDefined;
import com.maxwellnie.demo.exception.ProxyExtendsException;
import com.maxwellnie.demo.info.MethodProxyInfo;
import com.maxwellnie.demo.info.impl.ProxyInfo;
import com.maxwellnie.demo.info.impl.SimpleMethodProxyInfo;
import com.maxwellnie.demo.utils.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>代理类信息解析器的实现</p>
 * <p> Implementation of proxy class information parser </p>
 * @author Maxwell Nie
 */
public class SimpleProxyInfoParser implements ProxyInfoParser{
    @Override
    public ProxyInfo parse(Class<?> clazz) {
        try {
            return parse(ReflectionUtils.newInstance(clazz));
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            throw new ProxyExtendsException(e);
        }
    }
    /**
     * <p>获取对象的注解分析对象所要代理的的接口及方法，并且对方法进行解析，进一步确定方法的特征。</p>
     * <p>代理工厂将会使用这些信息建立起被代理对象和该对象的桥梁</p>
     * <p> Get the annotation of the object Analyze the interface and method that the object wants to proxy, and analyze the method to further determine the characteristics of the method. </p>
     * <p> The agent factory will use this information to build a bridge between the agent and the object </p>
     * @param proxyObject
     * @return ProxyInfo
     */
    @Override
    public ProxyInfo parse(Object proxyObject) {
        if (proxyObject == null)
            throw new ProxyExtendsException("Proxy obj is null");
        Class<?> clazz = proxyObject.getClass();
        ProxyDefined proxyDefined = clazz.getDeclaredAnnotation(ProxyDefined.class);
        ProxyInfo proxyInfo = new ProxyInfo();
        if(proxyDefined == null)
            return null;
        else{
            MethodProxyInfo[] methodProxyInfos = new MethodProxyInfo[0];
            Class<?>[] interfaces = proxyDefined.interfaces();
            for (Method method : clazz.getDeclaredMethods()){
                if(method.isAnnotationPresent(MethodInterceptor.class)) {
                    MethodProxyInfo methodProxyInfo = new SimpleMethodProxyInfo();
                    methodProxyInfo.setMethodName(method.getName());
                    methodProxyInfo.setParameterTypes(method.getParameterTypes());
                    methodProxyInfo.setProxyObject(proxyObject);
                    methodProxyInfo.setProxyMethod(method);
                    methodProxyInfo.setReturnType(method.getReturnType());
                    MethodProxyInfo[] newMethodProxyInfos = new MethodProxyInfo[methodProxyInfos.length + 1];
                    System.arraycopy(methodProxyInfos, 0, newMethodProxyInfos, 0, methodProxyInfos.length);
                    newMethodProxyInfos[methodProxyInfos.length] = methodProxyInfo;
                    methodProxyInfos = newMethodProxyInfos;
                }
            }
            proxyInfo.setProxyObject(proxyObject);
            proxyInfo.setClazz(clazz);
            proxyInfo.setMethodProxyInfos(methodProxyInfos);
            proxyInfo.setInterfaces(interfaces);
            return proxyInfo;
        }
    }
}
