package javaNote.jdkInvoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

/**
 * 有实现类方式实现jdk
 */
public class TestJDKInvokeOnClass {

    final static Logger logger = Logger.getLogger(TestJDKInvoke.class.getName());
    public static void main(String[] args){

        invocationHandlerimpl zhSayinvocationHandlerimpl = new invocationHandlerimpl(new ZhSay());
        HelloOnClass proxy = (HelloOnClass) (zhSayinvocationHandlerimpl.getProxy());
        logger.info(proxy.sayHello("nihaoss"));

    }
}
class invocationHandlerimpl implements  InvocationHandler{

    final static Logger logger = Logger.getLogger(invocationHandlerimpl.class.getName());
    //被代理类
    private Object proxyClass;
    public invocationHandlerimpl(Object proxyClass){
        this.proxyClass = proxyClass;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("invoke方法参数列表"+method);
        if(method.getName().equals("sayHello")){
           return  method.invoke(proxyClass,args);
        }
        return null;
    }

     public Object getProxy(){
        return Proxy.newProxyInstance(
                proxyClass.getClass().getClassLoader(),proxyClass.getClass().getInterfaces(),this);
     }

}

interface  HelloOnClass{
    String sayHello(String H);
}
class ZhSay implements  HelloOnClass{

    @Override
    public String sayHello(String H) {
        return H;
    }
}
class Ensay implements  HelloOnClass{


    @Override
    public String sayHello(String H) {
        return H;
    }
}

