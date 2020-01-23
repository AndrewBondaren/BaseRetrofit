package api.annotations;

import com.google.inject.ScopeAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({ METHOD, TYPE }) @Retention(RetentionPolicy.RUNTIME) @ScopeAnnotation
public @interface ThreadLocalScope {

}
