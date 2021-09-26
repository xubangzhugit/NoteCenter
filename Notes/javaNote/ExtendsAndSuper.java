package javaNote;

public class ExtendsAndSuper {

    public static void main(String[] args){

        //-------------------extends上界通配符-------------

        P<? extends Target> pUp = new P<>();
        //pUp.setObj(new Child()); //上界不能添加数据。编译器无法确定 P 所持有的类型， 但是可以接受现有的子类型P 赋值。
        pUp.getObj();// 取数据可以取。编译器知道pUp 里面的所有对象都是Target或他的子类。所以返回值都可以用pUp 接收；

        //-------------------super下界通配符-------------
        P<? super Target> pDown = new P<>();
        pDown.getObj();//可以获取值。会丢失数据类型。全为object
        pDown.setObj(new Child()); //可以设置target 或其子类对象
        //pDown.setObj(new Parent());//不能添加父类对象。编译器并不知道List的内容究竟是Father的哪个超类，因此不允许加入特定的任何超类
    }
}

/**
 * 定义泛型类
 * @param <T>
 */
class P<T>{
    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    private T obj;

}

/**
 * 父类
 */
class Parent {

}

/**
 * 目标类
 */
class Target extends Parent{

}

/**
 * 子类
 */
class Child extends Target{

}