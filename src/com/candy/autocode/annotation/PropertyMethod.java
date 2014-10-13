package com.candy.autocode.annotation;

import java.lang.annotation.*;

/**
 * User: yantingjun
 * Time: 14-8-11 : 下午4:28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyMethod {
    /**
     * If set then the JavaScript callback function name is obtained from a query parameter with the given name.
     */
    String value();
}
