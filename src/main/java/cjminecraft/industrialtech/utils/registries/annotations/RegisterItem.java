package cjminecraft.industrialtech.utils.registries.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface RegisterItem {

    String registryName();

    String unlocalizedName() default "";

}
