package com.chunyu.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 雨时光 on 2019-06-11 14:36
 * Email: 746431278@qq.com
 */
@Retention(RetentionPolicy.CLASS)
@Target(value = ElementType.FIELD)
public @interface RandomString {
}
