package com.Quovantis.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.Quovantis.anotation.ValidDate;

import static com.Quovantis.constant.ApplicationConstant.DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

    private Boolean isOptional;

    @Override
    public void initialize(ValidDate validDate) {
        this.isOptional = validDate.optional();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        boolean validDate = isValidFormat(DATE_FORMAT, value);

        return isOptional ? (validDate || (StringUtils.isEmpty(value) )) : validDate;
    }

    private static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (value != null){
                date = sdf.parse(value);
                if (!value.equals(sdf.format(date))) {
                    date = null;
                }
            }

        } catch (ParseException ex) {
        }
        return date != null;
    }
}
