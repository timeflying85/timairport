package be.technifutur.java.timairport.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DayInPastValidator.class)
public @interface DayInPast {

//    long minDays() default 0;

    String message() default "Register date should be more in the past";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int value() default 7;
    ChronoUnit unit() default ChronoUnit.DAYS;


}
