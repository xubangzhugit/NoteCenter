package javaNote.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = MyValidMethod.class) //实现校验器接口
//自定义校验注解
public @interface CustomAnnotation {
    String message(); //这边可以标注默认的验证失败消息
    Class<?>[] groups() default { };
}
/*class MyCuctom implements  ConstraintValidator<CustomAnnotation, Object> {
    *//**
     * 初始化方法
     *
     *//*
    @Override
    public void initialize(MyValid constraintAnnotation) {
        System.out.println("自定义验证初始化");
    }


    *//**
     * 验证方法
     * 验证成功返回: true
     * 验证失败返回: false
     *//*
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}*/
