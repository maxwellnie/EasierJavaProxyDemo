# 这是一个关于如何通过编程更便捷地使用Java代理的演示
## 它可以做什么？
1.可以为你提供一个对于Java代理实现的新思路
2.为Java代理提供了一种更便捷的方式，可以使用注解在与目标类毫不相关的类定义代理的实现
## 它的原理？
1.通过注解定义代理对象的细节
2.对这些细节进行处理，建立代理对象和目标对象的桥梁
3.使用反射调用代理方法的实现
## 它怎么用？
0.编写接口、实现、代理类
1.创建 DefaultProxyObjectFactory、SimpleProxyInfoParser对象
2.通过SimpleProxyInfoParser解析代理信息，得到ProxyInfo对象
3.通过DefaultProxyObjectFactory创建代理对象
下面我将按序号依次介绍
## 0.编写代理类
接口：
```java
/**
 * @author Maxwell Nie
 */
public interface Interface1 {
    void hello();
    void hi();
}
```
实现：
```java
/**
 * @author Maxwell Nie
 */
public class Interface1Impl implements Interface1{
    @Override
    public void hello() {
        System.out.println("Hello.");
    }

    @Override
    public void hi() {
        System.out.println("Hi.");
    }
}
```
代理类：
```java
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
```
## 1.创建 DefaultProxyObjectFactory、SimpleProxyInfoParser对象
```java
static final ProxyObjectFactory<Object> PROXY_OBJECT_FACTORY = new DefaultProxyObjectFactory();
static final ProxyInfoParser PROXY_INFO_PARSER = new SimpleProxyInfoParser();
```
## 2.通过SimpleProxyInfoParser解析代理信息，得到ProxyInfo对象
```java
Interface1 impl = new Interface1Impl();
impl.hi();
impl.hello();
ProxyInfo proxyInfo = PROXY_INFO_PARSER.parse(ProxyImpl.class);
```
## 3.通过DefaultProxyObjectFactory创建代理对象
```java
if(proxyInfo != null){
    Object proxyObject = PROXY_OBJECT_FACTORY.produce(impl, proxyInfo);
    impl = (Interface1)proxyObject;
    impl.hi();
    impl.hello();
}else {
    System.out.println("proxyInfo is null");
}
```
## 4.运行结果
```text
hi world!
hello world!
I'm here.
I'm coming.
```