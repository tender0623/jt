package com.jt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CGB on 2019/4/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    int index() default 0;
    CACHE_TYPE cachetType() default CACHE_TYPE.FIND;
    enum  CACHE_TYPE{
        FIND,UPDATE;
    }
}
