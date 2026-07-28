package io.github.ninobomba.utils.spring.events.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApplicationSqlInjectionAttackEvent` keeps message and source values. */
class ApplicationSqlInjectionAttackEvent_Stores_Message {

    @DisplayName ( "ApplicationSqlInjectionAttackEvent stores source and message" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String message, boolean withClock ) {
        // given
        var source = new Object ( );

        // when
        var event = withClock
                ? new ApplicationSqlInjectionAttackEvent ( source, Clock.systemUTC ( ), message )
                : new ApplicationSqlInjectionAttackEvent ( source, message );

        // then
        assertThat ( event.getSource ( ) ).isEqualTo ( source );
        assertThat ( event.getMessage ( ) ).isEqualTo ( message );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "sql injection detected", true ),
                Arguments.of ( "sql injection detected", false ),
                Arguments.of ( null, false )
        );
    }
}
