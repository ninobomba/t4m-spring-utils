package io.github.ninobomba.utils.spring.api.token;

import org.springframework.web.context.request.ServletWebRequest;

import java.util.Optional;

public interface IUserTokenValidator {

    Optional<String> validateAndGetToken(ServletWebRequest webRequest);

    Boolean isValid(String token);
}
