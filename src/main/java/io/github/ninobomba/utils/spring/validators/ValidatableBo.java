package io.github.ninobomba.utils.spring.validators;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.slf4j.LoggerFactory;
import java.util.List;

public interface ValidatableBo {

    // Thread-safe, lazy, reused - no need for shutdown hook in Spring Boot
    final class ValidatorHolder {
        private ValidatorHolder() {}
        static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
        static final Validator VALIDATOR = FACTORY.getValidator();
    }

    default List<String> getValidationErrors() {
        return ValidatorHolder.VALIDATOR.validate(this).stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .toList();
    }

    default String getValidationErrorsAsString() {
        return String.join(System.lineSeparator(), getValidationErrors());
    }

    default boolean isValid() {
        return ValidatorHolder.VALIDATOR.validate(this).isEmpty();
    }

    default boolean isValid(boolean logOnError) {
        var violations = ValidatorHolder.VALIDATOR.validate(this);
        if (logOnError && !violations.isEmpty()) {
            LoggerFactory.getLogger(getClass())
                    .debug("Validation failed for {}: {}", getClass().getSimpleName(), violations);
        }
        return violations.isEmpty();
    }

    default void validateOrThrow() {
        var errors = getValidationErrors();
        if (!errors.isEmpty()) {
            throw new jakarta.validation.ConstraintViolationException(
                    String.join(", ", errors), ValidatorHolder.VALIDATOR.validate(this));
        }
    }
}