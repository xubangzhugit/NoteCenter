# java
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