package io.github.ninobomba.utils.spring.api.token;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `UserTokenValidator` reads and validates token from request attributes. */
class UserTokenValidator_ValidateAndGetToken_From_Request {

    private static final Object ABSENT = new Object ( );

    @DisplayName ( "UserTokenValidator returns token only when present and valid" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( boolean useNullWebRequest, Object tokenAttribute, int maxTokenLength, String expectedToken ) {
        // given
        var validator = new UserTokenValidator ( );
        ReflectionTestUtils.setField ( validator, "maxTokenLength", maxTokenLength );

        ServletWebRequest webRequest = null;
        if ( !useNullWebRequest ) {
            var request = new MockHttpServletRequest ( );
            if ( tokenAttribute != ABSENT ) {
                request.setAttribute ( "TOKEN", tokenAttribute );
            }
            webRequest = new ServletWebRequest ( request );
        }

        // when
        var actual = validator.validateAndGetToken ( webRequest );

        // then
        if ( expectedToken == null ) {
            assertThat ( actual ).isEmpty ( );
        } else {
            assertThat ( actual ).contains ( expectedToken );
        }
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( true, ABSENT, 10, null ),
                Arguments.of ( false, ABSENT, 10, null ),
                Arguments.of ( false, "abc", 3, "abc" ),
                Arguments.of ( false, 12345, 10, "12345" ),
                Arguments.of ( false, "abcd", 3, null )
        );
    }
}
