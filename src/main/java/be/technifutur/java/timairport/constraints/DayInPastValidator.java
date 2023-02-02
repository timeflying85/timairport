package be.technifutur.java.timairport.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DayInPastValidator implements ConstraintValidator<DayInPast, LocalDate> {


    private DayInPast constraint;

    @Override
    public void initialize(DayInPast constraintAnnotation) {
        constraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if( !value.plus( constraint.value(), constraint.unit() ).isBefore( LocalDate.now() ) ){
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                    String.format("Should minimum %s %s in the past",
                            constraint.value(),
                            constraint.unit().toString().toLowerCase())
            ).addConstraintViolation();

            return false;
        }

        return true;
    }

//    private long minDays;
//
//    @Override
//    public void initialize(DayInPast constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//        this.minDays = constraintAnnotation.minDays();
//    }
//
//    LocalDate now = LocalDate.now();
//
//
//    @Override
//    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
//        long diffInDays = now.toEpochDay() - date.toEpochDay();
//        if(date.isBefore(LocalDate.now().minusDays(minDays)))
//            return true;
//
//
//        context.disableDefaultConstraintViolation();
//        context.buildConstraintViolationWithTemplate("Registration date should be "+minDays+" days in the past")
//                .addConstraintViolation();
//        return false;
//    }

}

//    @Override
//    public boolean isValid(LocalDate day, ConstraintValidatorContext constraintValidatorContext) {
//
//        LocalDate todayMinusSevenDays = LocalDate.now().minusDays(7);
//
//        return !day.isAfter(todayMinusSevenDays);
//
//    }







