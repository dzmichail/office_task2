package app.annotation.validation.validator;

import app.annotation.validation.UserConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;


public class UserConstraintValidator implements ConstraintValidator<UserConstraint, Date> {
    public boolean isValid(Date date, ConstraintValidatorContext context){
        Date dateNow = new Date();

        int daysAndHours=365*24;

        return true;
    }
}
