package com.chunyu.utils;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by 雨时光 on 2019-06-11 14:38
 * Email: 746431278@qq.com
 */
public class RandomUtil {

    private static final String RANDOM_SUFFIX = "_Random";

    private RandomUtil() {

    }

    public static void inject(Object object) {
        try {
            Class bindingClass = Class.forName(object.getClass().getCanonicalName() + RANDOM_SUFFIX);
            Constructor constructor = bindingClass.getConstructor(object.getClass());
            constructor.newInstance(object);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
