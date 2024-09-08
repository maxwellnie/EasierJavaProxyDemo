package com.maxwellnie.demo.info;

import java.lang.reflect.Method;

/**
 * <p>被代理方法的特征</p>
 * <p>Characteristics of the agent method</p>
 * @author Maxwell Nie
 */
public interface MethodProxyInfo {
    void setMethodName(String methodName);
    /**
     * <p>被代理方法的名称</p>
     * <p>The name of the agent method</p>
     * @return method name
     */
    String getMethodName();
    void setParameterTypes(Class<?>[] parameterTypes);
    /**
     * <p>被代理方法的参数类型</p>
     * <p>The parameter types of the agent method</p>
     * @return parameter types
     */
    Class<?>[] getParameterTypes();
    void setReturnType(Class<?> returnType);
    /**
     * <p>被代理方法的返回类型</p>
     * <p>The return type of the agent method</p>
     * @return return type
     */
    Class<?> getReturnType();
    void setProxyMethod(Method method);
    /**
     * <p>代理对象的方法</p>
     * <p>The proxy method</p>
     * @return method
     */
    Method getProxyMethod();
    void setProxyObject(Object proxyObject);
    /**
     * <p>代理对象</p>
     * <p>The proxy object</p>
     * @return proxy object
     */
    Object getProxyObject();
}
