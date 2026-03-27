package io.github.ninobomba.utils.spring.mappings;

import org.springframework.stereotype.Component;

@Component
public class PasswordMapper {

    public String map(char[] value) {
        return value == null ? null : new String(value);
    }

    public char[] map(String value) {
        return value == null ? null : value.toCharArray();
    }

}
