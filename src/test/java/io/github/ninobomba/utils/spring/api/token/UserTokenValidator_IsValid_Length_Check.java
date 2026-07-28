package io.github.ninobomba.utils.spring.api.token;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `UserTokenValidator` validates token length boundaries. */
class UserTokenValidator_IsValid_Length_Check {

    @DisplayName ( "UserTokenValidator checks token max length" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String token, int maxTokenLength, boolean expected ) {
        // given
        var validator = new UserTokenValidator ( );
        ReflectionTestUtils.setField ( validator, "maxTokenLength", maxTokenLength );

        // when
        var actual = validator.isValid ( token );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, 10, false ),
                Arguments.of ( "", 0, true ),
                Arguments.of ( "abc", 3, true ),
                Arguments.of ( "abcd", 3, false )
        );
    }
}
