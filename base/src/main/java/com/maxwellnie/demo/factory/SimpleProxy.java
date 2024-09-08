package com.maxwellnie.demo.factory;


import com.maxwellnie.demo.config.JavaByteCodeConfiguration;
import com.maxwellnie.demo.info.MethodProxyInfo;
import com.maxwellnie.demo.utils.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
/**
 * 代理对象
 * @author Maxwell Nie
 */
public class SimpleProxy implements InvocationHandler {
    final Map<String, MethodProxyInfo> proxyInfoMap;
    final Object target;

    public SimpleProxy(Map<String, MethodProxyInfo> proxyInfoMap, Object target) {
        this.proxyInfoMap = proxyInfoMap;
        this.target = target;
    }

    /**
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodProxyKey = StringUtils.getMethodDeclaredName(method);
        if (Object.class.equals(method.getDeclaringClass())) {

            return method.invoke(this, args);

        } else if (proxyInfoMap.containsKey(methodProxyKey)) {

            MethodProxyInfo methodProxyInfo = proxyInfoMap.get(methodProxyKey);
            return methodProxyInfo.getProxyMethod().invoke(methodProxyInfo.getProxyObject(), args);

        } else if (method.isDefault())

            return JavaByteCodeConfiguration.handleDefaultMethod(method, target, args);

        else

            return method.invoke(target, args);
    }
}
