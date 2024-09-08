This is a demonstration on how to programmatically make it easier to use Java agents
## What can it do?
1. Can provide you with a new idea for Java proxy implementation
2. Provides a more convenient way for Java agents to use annotations to define proxy implementations in classes that are unrelated to the target class
## How does it work?
1.Define the details of the proxy object through annotations
2.Handle these details and establish a bridge between the proxy object and the target object
3.Use reflection to invoke the implementation of proxy methods
## How to use?
0.Write interface, implementation, proxy classes
1.Create DefaultProxyObjectFactory, SimpleProxyInfoParser object
2.Run the SimpleProxyInfoParser command to parse the proxy information and obtain the ProxyInfo object
3.Create a proxy object by DefaultProxyObjectFactory
I will introduce them in sequence below
## 0.proxy classes
interface：
```java
/**
 * @author Maxwell Nie
 */
public interface Interface1 {
    void hello();
    void hi();
}
```
implementation：
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
proxy classes：
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
## 1.create DefaultProxyObjectFactory and SimpleProxyInfoParser object
```java
static final ProxyObjectFactory<Object> PROXY_OBJECT_FACTORY = new DefaultProxyObjectFactory();
static final ProxyInfoParser PROXY_INFO_PARSER = new SimpleProxyInfoParser();
```
## 2.Run the SimpleProxyInfoParser command to parse the proxy information and obtain the ProxyInfo object
```java
Interface1 impl = new Interface1Impl();
impl.hi();
impl.hello();
ProxyInfo proxyInfo = PROXY_INFO_PARSER.parse(ProxyImpl.class);
```
## 3.Create a proxy object by DefaultProxyObjectFactory
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
## 4.result
```text
hi world!
hello world!
I'm here.
I'm coming.
```