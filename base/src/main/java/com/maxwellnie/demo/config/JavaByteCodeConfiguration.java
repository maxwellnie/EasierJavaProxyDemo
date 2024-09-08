package com.maxwellnie.demo.config;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>由于JDK8与JDK9的DefaultMethod处理方式有些不同，所以需要通过反射来处理。<p/>
 * <p> Because JDK8 and JDK9 handle DefaultMethod somewhat differently, it needs to be handled by reflection. <p/>
 * @author Maxwell Nie
 */
public class JavaByteCodeConfiguration {
    /**
     * JDK9+ introduces a novel method named "privateLookupIn" to handle PRIVATE and PROTECTED methods.
     *
     * @since 1.0
     */
    public static final Method highJavaVersionLookUpMethod;
    /**
     * JDK8 it is necessary to use an invisible constructor to instantiate the LookUp class to handle PRIVATE and PROTECTED methods.
     *
     * @since 1.0
     */
    public static final Constructor<MethodHandles.Lookup> java8LookupConstructor;

    //since 1.0
    static {
        //java9+ requires the privateLookupIn method to handle the default method
        Method privateLookupIn;
        try {
            privateLookupIn = MethodHandles.class.getMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
        } catch (NoSuchMethodException e) {
            privateLookupIn = null;
        }
        highJavaVersionLookUpMethod = privateLookupIn;
        //java8, you need to get the Lookup private constructor to create a MethodHandle to call the default method of the object
        Constructor<MethodHandles.Lookup> lookup = null;
        if (highJavaVersionLookUpMethod == null) {
            try {
                lookup = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, int.class);
                lookup.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                lookup = null;
            }
        }
        java8LookupConstructor = lookup;
    }
    /**
     * @param method
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @since 1.0
     */
    private static MethodHandle getHighJavaVersionMethodHandle(Method method)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final Class<?> declaringClass = method.getDeclaringClass();
        return ((MethodHandles.Lookup) highJavaVersionLookUpMethod.invoke(null, declaringClass, MethodHandles.lookup())).findSpecial(
                declaringClass, method.getName(), MethodType.methodType(method.getReturnType(), method.getParameterTypes()),
                declaringClass);
    }

    /**
     * @param method
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @since 1.0
     */
    private static MethodHandle getJava8MethodHandle(Method method)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        final Class<?> declaringClass = method.getDeclaringClass();
        return java8LookupConstructor.newInstance(declaringClass, MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED).unreflectSpecial(method, declaringClass);
    }
    public static Object handleDefaultMethod(Method method, Object proxy, Object[] args) throws Throwable {
        MethodHandle methodHandle = getJava8MethodHandle(method);
        if (highJavaVersionLookUpMethod == null)
            return methodHandle.bindTo(proxy).invokeWithArguments(args);
        else
            return getHighJavaVersionMethodHandle(method).bindTo(proxy).invokeWithArguments(args);
    }
}
