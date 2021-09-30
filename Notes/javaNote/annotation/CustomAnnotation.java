package javaNote.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = MyValidMethod.class) //实现校验器接口
//自定义校验注解
@Constraint(validatedBy = MyCuctom.class)
public @interface CustomAnnotation {
    String message(); //这边可以标注默认的验证失败消息
    Class<?>[] groups() default { };
}
class MyCuctom implements ConstraintValidator<CustomAnnotation,  String> {

    @Override
    public void initialize(CustomAnnotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        if("hello".equals(o)){
            return true;
        }
        return false;
    }
}
