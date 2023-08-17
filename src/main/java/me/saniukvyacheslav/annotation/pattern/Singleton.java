package me.saniukvyacheslav.annotation.pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Singleton annotation indicate that annotated class implements "Singleton" pattern.
 * Mark annotation.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Singleton {

}
