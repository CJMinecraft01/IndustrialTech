package cjminecraft.industrialtech.utils.registries.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RegisterItem {

    String registryName();

    String unlocalizedName() default "";

    String itemBlockClassName() default "net.minecraft.item.ItemBlock";

}
