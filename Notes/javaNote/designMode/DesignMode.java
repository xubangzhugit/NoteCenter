package javaNote.designMode;

import com.sun.org.apache.bcel.internal.util.SecuritySupport;
import org.bouncycastle.util.Strings;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 设计模式
 */
public class DesignMode {

    public static void main(String[] args) {


    }
}

//-------------------- 工厂方法----------------/
class FactoryMethod {
    public static void main(String[] args){
        Factory getimpl = Factory.getimpl();
        getimpl.parse();
    }

}
interface Factory {
    String parse();
    Factory fa = new FacrotyImpl();

    /**
     * 获取子类对象
     * @return
     */
    public static Factory getimpl(){
        return  fa;
    }
}
class FacrotyImpl implements Factory {

    @Override
    public String parse() {
        return new String();
    }

}
//------------------静态工厂方法--------------------
class StaticFacroty{
    public static void main(String[] args){
        //Number parse = Facroty.parseNumber();
        //String s = Facroty.parseString();
        Facroty.parseString(20200220).toString();
    }

}
class Facroty{
    public static Number parseNumber(){
        return new BigDecimal("");
    }
    public static String parseString(){
        return new String("");
    }
    public static LocalDate parseString(int yyyymmdd){
        String str = String.valueOf(yyyymmdd);
        LocalDate of = LocalDate.of(Integer.valueOf(str.substring(0, 4)), Integer.valueOf(str.substring(4, 6)), Integer.valueOf(str.substring(6)));
        return of;
    }

}
//------------------抽象工厂-----------------
//--------------------生成器---------------
//-------------------单例----------
class Single{
    private Single(){

    }
    private final static Single sin = new Single();
     public  static Single getInstance(){
         return sin;
     }
}
class SecuritySingle{
    private volatile static SecuritySingle secusin = null;
    private SecuritySingle(){

    }
    public static SecuritySingle getinstance(){
         if(secusin==null){
            synchronized (SecuritySingle.class){
                  if(secusin==null){
                       return new SecuritySingle();
                  }
            }
         }
         return secusin;
    }
}
//------适配器模式(futrueTsk)-----
/**
 * 编写一个Adapter的步骤如下：
 *      实现目标接口，这里是Runnable；
 *      内部持有一个待转换接口的引用，这里是通过字段持有Callable接口；
 *      在目标接口的实现方法内部，调用待转换接口的方法
 */
//--------桥接模式()--------
  //避免直接继承造成的子类爆炸
//------------组合(xml 树解析)---------------
//----------------装饰器模式(inputstream 类型包装)------------
//--------------------外观模式(类似网关，不用访问具体的细节对象，和中间对象交互即可)-------------
//----------------------享元模式(共享原数据：类似于缓存的概念)------------------------
//-----------------------代理模式------------------
//---------------------责任链模式(过滤器，拦截器)------------------
//-----------------------观察者模式：发布订阅模式------------------------













