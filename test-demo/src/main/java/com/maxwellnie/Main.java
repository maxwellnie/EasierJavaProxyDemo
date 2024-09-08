package com.maxwellnie;

import com.maxwellnie.demo.factory.DefaultProxyObjectFactory;
import com.maxwellnie.demo.factory.ProxyObjectFactory;
import com.maxwellnie.demo.info.impl.ProxyInfo;
import com.maxwellnie.demo.parser.ProxyInfoParser;
import com.maxwellnie.demo.parser.SimpleProxyInfoParser;

public class Main {
    static final ProxyObjectFactory<Object> PROXY_OBJECT_FACTORY = new DefaultProxyObjectFactory();
    static final ProxyInfoParser PROXY_INFO_PARSER = new SimpleProxyInfoParser();
    public static void main(String[] args) {
        Interface1 impl = new Interface1Impl();
        impl.hi();
        impl.hello();
        ProxyInfo proxyInfo = PROXY_INFO_PARSER.parse(ProxyImpl.class);
        if(proxyInfo != null){
            Object proxyObject = PROXY_OBJECT_FACTORY.produce(impl, proxyInfo);
            impl = (Interface1)proxyObject;
            impl.hi();
            impl.hello();
        }else {
            System.out.println("proxyInfo is null");
        }
    }
}