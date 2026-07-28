package io.github.ninobomba.utils.spring.profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ActiveProfileUtils` masks detailed messages in production. */
class ActiveProfileUtils_Returns_Detailed_Message {

    @DisplayName ( "ActiveProfileUtils returns default message for production" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String message, String profile, String expected ) {
        // given
        var originalMessage = message;

        // when
        var actual = ActiveProfileUtils.getDetailedMessage ( originalMessage, profile );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "custom error", "dev", "custom error" ),
                Arguments.of ( "custom error", "prod", "Contact the administrator for more information" ),
                Arguments.of ( "custom error", " production ", "Contact the administrator for more information" )
        );
    }
}
