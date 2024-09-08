package com.maxwellnie.demo.parser;


import com.maxwellnie.demo.info.impl.ProxyInfo;

/**
 * <p>解析代理类的信息<p/>
 * <p> Parsing information for the proxy class <p/>
 * @author Maxwell Nie
 */
public interface ProxyInfoParser {
    /**
     * @param obj
     * @return ProxyInfo
     */
    ProxyInfo parse(Object obj);
    /**
     * @param clazz
     * @return ProxyInfo
     */
    ProxyInfo parse(Class<?> clazz);
}
