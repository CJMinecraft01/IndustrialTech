package cjminecraft.industrialtech.utils.registries.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RegisterItemBlock {

    String registryName();

    String unlocalizedName() default "";

    boolean customItemBlock() default false;

}
