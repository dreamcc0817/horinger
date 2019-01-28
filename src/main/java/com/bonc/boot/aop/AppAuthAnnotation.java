package com.bonc.boot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈自定义注解〉
 *
 * @author TanRW
 * @create 2018/11/5
 * @since 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AppAuthAnnotation {
	 boolean userId() default false;
}