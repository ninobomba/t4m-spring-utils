package io.github.ninobomba.utils.spring.events.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApplicationSecurityEvent` keeps message and source values. */
class ApplicationSecurityEvent_Stores_Message {

    @DisplayName ( "ApplicationSecurityEvent stores source and message" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String message, boolean withClock ) {
        // given
        var source = new Object ( );

        // when
        var event = withClock
                ? new ApplicationSecurityEvent ( source, Clock.systemUTC ( ), message )
                : new ApplicationSecurityEvent ( source, message );

        // then
        assertThat ( event.getSource ( ) ).isEqualTo ( source );
        assertThat ( event.getMessage ( ) ).isEqualTo ( message );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "security alert", true ),
                Arguments.of ( "security alert", false ),
                Arguments.of ( null, false )
        );
    }
}
