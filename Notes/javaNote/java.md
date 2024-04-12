# java

# 数组copy的方式

System.copyArray(Object src,int srcPos,Object dest,int destPos,int length);
Object src : 原数组
int srcPos : 从元数据的起始位置开始
Object dest : 目标数组
int destPos : 目标数组的开始起始位置
int length : 要copy的数组的长度
for
clone
Arrays.copyOf()

# 接口

- 接口中定义的非default方法，实现类必须实现，接口中定义的default 方法，实现类可以选择性的实现。
- import static java.lang.System.*;(可以导入一个类的静态字段和静态方法)

# 断言

- 断言是一种调试方式，断言失败会抛出AssertionError，只能在开发和测试阶段启用断言
- assert a == b; 断言默认关闭(使用参数-es开启)

# 反射

- setAccessible(true)修改访问权限
- logger.info("修饰符"+name.getModifiers());返回值int代表的类型
  /**
  * public: 1
  * private: 2
  * protected: 4
  * Static: 8
  * final: 16
  * synchronized: 32
  * volatile: 64
  * transient: 128
  * native: 256
  * interface: 512
  * abstract: 1024
  * strict: 2048
  */

# 动态代理

- JDK 动态代理，JDK 动态代理的类必须实现一个接口，而且生成的代理类是其接口的实现类，也就是被代理的类的兄弟类，由JDK内部实现
- cglib代理的类，无需强制实现接口，其生成的代理类 是 被代理类的子类，并且重写的被代理类的方法，而且需要额外的引入Jar

# 注解

- java使用@interface 声明注解
- 元注解： 可以修饰其他注解的注解
    - @Target : 指定注解可以标注的位置。
        - 类或接口：ElementType.TYPE
        - 字段：ElementType.FIELD
        - 方法：ElementType.METHOD
        - 构造方法：ElementType.CONSTRUCTOR
        - 方法参数：ElementType.PARAMETER
    - @Retention : 指定注解的声明周期
        - 仅编译期：RetentionPolicy.SOURCE；
        - 仅class文件：RetentionPolicy.CLASS；
        - 运行期：RetentionPolicy.RUNTIME。
    - @Repeatable: 指定注解是否可以重复

    - 注解可以配合反射，完成参数值的校验

# 泛型

- 定义一个模板 ,Java泛型的实现方式——擦拭法,定义的泛型，jvm 会自动转为object
- 局限性 （object，不能存放基本数据类型）
    - 局限一：<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型：
    - 无法取得带泛型的Class。因为泛型会转object,所以所有存放的数据都是object
    - 局限三：无法判断带泛型的类型：
    - 局限四：不能实例化T类型
- <? extends obejct>
  - 使用类似<? extends Number>通配符作为方法参数时表示
    方法内部可以调用获取Number引用的方法，例如：Number n = obj.getFirst();
    方法内部无法调用传入Number引用的方法（null除外），例如：obj.setFirst(Number n);
    即一句话总结：使用extends通配符表示可以读，不能写


 ```
 父子类执行顺序
    new child():
    1: 先执行父类的静态代码块 static {}
    2: 执行子类的静态代码块 static{}
    3: 执行父类的初始化代码 {}
    4: 执行父类的构造方法  (){}
    5: 执行子类的初始化代码 {}
    6: 执行子类的构造方法  (){}

java 对象copy
「浅拷贝」：
    创建一个新对象，然后将当前对象的非静态字段复制到该对象，
    如果字段类型是值类型（基本类型跟String）的，那么对该字段进行「复制」；
    如果字段是引用类型的，「则只复制该字段的引用而不复制引用指向的对象(也就是只复制对象的地址)」。
    此时新对象里面的引用类型字段相当于是原始对象里面引用类型字段的一个副本，
    原始对象与新对象里面的引用字段指向的是同一个对象。
    因此，修改clonePerson里面的address内容时，原person里面的address内容会跟着改变。
「深拷贝」：(序列化和反序列化实现)
    序列化是将对象写到流中便于传输，而反序列化则是把对象从流中读取出来。这里写到流中的对象则是原始对象的一个拷贝，因为原始对象还存在 JVM 中，
    所以可以利用对象的序列化产生克隆对象，然后通过反序列化获取这个对象。
    注意每个需要序列化的类都要实现 Serializable 接口，如果有某个属性不需要序列化，可以将其声明为 transient，即将其排除在克隆属性之外
```