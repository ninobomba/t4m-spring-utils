package io.github.ninobomba.utils.spring.events.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApplicationXSSAttackEvent` keeps message and source values. */
class ApplicationXSSAttackEvent_Stores_Message {

    @DisplayName ( "ApplicationXSSAttackEvent stores source and message" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String message, boolean withClock ) {
        // given
        var source = new Object ( );

        // when
        var event = withClock
                ? new ApplicationXSSAttackEvent ( source, Clock.systemUTC ( ), message )
                : new ApplicationXSSAttackEvent ( source, message );

        // then
        assertThat ( event.getSource ( ) ).isEqualTo ( source );
        assertThat ( event.getMessage ( ) ).isEqualTo ( message );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "xss detected", true ),
                Arguments.of ( "xss detected", false ),
                Arguments.of ( null, false )
        );
    }
}
