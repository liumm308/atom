package com.library.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ActField {

    /**
     * 显示名字
     * @return
     */
    String name();

    /**
     * 是否用于分支条件判断
     * @return
     */
    boolean isJudg() default false;
}
