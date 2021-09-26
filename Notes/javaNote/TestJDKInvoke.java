package javaNote;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

/**
 * 无实现类方式实现jdk
 */
public class TestJDKInvoke {

    final static Logger logger = Logger.getLogger(TestJDKInvoke.class.getName());
    public static void main(String[] args){

        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //类似前置通知
                if("sayHello".equals(method.getName())){

                  return "sayhello";
                }
                //后置通知
                return "hello";

            }
        };
        Hello o = (Hello)Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[]{Hello.class},
                handler
        );
        logger.info(o.sayHello("hello"));

    }
}

interface  Hello{
    String sayHello(String H);
}
