package com.yqz.zzh.elasticsearch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zzh
 * @Description
 * @date 2020-09-23 19:47
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {
    String description();//excel表头对应的名称
    int order();//表头排列的顺序
}
