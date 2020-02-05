package main.java.ru;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value= ElementType.METHOD)
@Retention(RUNTIME)
public @interface Log {
}