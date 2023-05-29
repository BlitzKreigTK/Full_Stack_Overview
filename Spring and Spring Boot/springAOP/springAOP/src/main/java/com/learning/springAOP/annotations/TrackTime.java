package com.learning.springAOP.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//Only at Runtime
@Target(ElementType.METHOD)
//Applicable for Method only
public @interface TrackTime {

}
