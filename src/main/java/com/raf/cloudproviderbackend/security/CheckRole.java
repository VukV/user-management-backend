package com.raf.cloudproviderbackend.security;

import com.raf.cloudproviderbackend.model.user.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckRole {

    RoleEnum[] roles() default {};

}
