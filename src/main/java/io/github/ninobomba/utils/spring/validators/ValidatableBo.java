package io.github.ninobomba.utils.spring.validators;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface ValidatableBo {

    Logger log = LoggerFactory.getLogger(ValidatableBo.class);

    ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    default boolean isValid() {
        return isValid(false);
    }

    default boolean isValid(boolean logDataOnError) {
        List<String> errors = getValidationErrors();
        if (logDataOnError && !errors.isEmpty()) {
            errors.forEach(log::debug);
        }
        return errors.isEmpty();
    }

    default List<String> getValidationErrors() {
        return VALIDATOR.validate(this).stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();
    }
}
