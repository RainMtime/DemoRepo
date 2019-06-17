package com.chunyu.annotations;

/**
 * Created by 雨时光 on 2019-06-11 14:35
 * Email: 746431278@qq.com
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(value = ElementType.FIELD)
public @interface RandomInt{
    int minValue() default 0;
    int maxValue() default 65535;
}