package io.github.ninobomba.utils.spring.web.agent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `HttpParameterSanitizer` strips forbidden control characters. */
class HttpParameterSanitizer_Removes_Forbidden_Control_Characters {

    @DisplayName ( "HttpParameterSanitizer removes forbidden control chars" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String input, String expected ) {
        // given
        var value = input;

        // when
        var actual = HttpParameterSanitizer.sanitize ( value );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null ),
                Arguments.of ( "abc", "abc" ),
                Arguments.of ( "ab\u0000cd", "abcd" ),
                Arguments.of ( "a\n\tb\u0007", "a\n\tb" )
        );
    }
}
