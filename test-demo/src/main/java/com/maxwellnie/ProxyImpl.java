package com.maxwellnie;

import com.maxwellnie.demo.annoation.MethodInterceptor;
import com.maxwellnie.demo.annoation.ProxyDefined;

/**
 * @author Maxwell Nie
 */
@ProxyDefined(interfaces = {Interface1.class})
public class ProxyImpl {
    @MethodInterceptor
    public void hello(){
        System.out.println("I'm coming.");
    }
    @MethodInterceptor
    public void hi(){
        System.out.println("I'm here.");
    }
}
