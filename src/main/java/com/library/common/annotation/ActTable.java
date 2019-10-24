package com.library.common.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActTable {

    /**
     * 业务表名
     * @return
     */
    String tableName();

    /**
     * 数据库主键
     * @return
     */
    String pkName();
}
