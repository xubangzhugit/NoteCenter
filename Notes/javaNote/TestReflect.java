package javaNote;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TestReflect {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String id;
     static final Logger logger  = Logger.getLogger(TestReflect.class.getName());
    public static void main(String[] args) throws Exception{

        String s = "Hi %s, your score is %d!";
        logger.info(String.format(s,"mars",99));
        //断言(默认关闭，使用vm 参数 -es 开启)
        assert s == null;
        //获取私有字段
        final Class<TestReflect> testClass = TestReflect.class;
        Field name = testClass.getDeclaredField("name");
        logger.info("getDeclaredField"+name);
        logger.info("字段名"+name.getName());
        logger.info("字段类型"+name.getType());
        logger.info("修饰符"+name.getModifiers());
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
        //所有public 字段
        Field[] fields = testClass.getFields();
        for (Field field : fields){
            logger.info("field---->"+field.getName());
        }
        //所有字段（包含非public的）
        Field[] DeclaredFields = testClass.getDeclaredFields();
        for (Field field : DeclaredFields){
            logger.info("DeclaredFields---->"+field.getName());
        }
        //获取方法，调用
        TestReflect TestReflect = new TestReflect();
        Method setmethod = testClass.getMethod("setName", String.class);
        Object methodReturn = setmethod.invoke(TestReflect, "xubangzhu");
        logger.info("方法调用 "+ TestReflect.getName());
        //创建实例
        TestReflect test1 = testClass.getConstructor().newInstance();
        ArrayList al = new ArrayList();
        int i = 1;
        al.add(i);
        logger.info(al.get(0)+"????");

    }
}
