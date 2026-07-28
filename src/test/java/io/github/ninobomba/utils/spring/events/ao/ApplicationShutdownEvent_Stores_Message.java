package io.github.ninobomba.utils.spring.events.ao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApplicationShutdownEvent` keeps message and source values. */
class ApplicationShutdownEvent_Stores_Message {

    @DisplayName ( "ApplicationShutdownEvent stores source and message" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String message, boolean withClock ) {
        // given
        var source = new Object ( );

        // when
        var event = withClock
                ? new ApplicationShutdownEvent ( source, Clock.systemUTC ( ), message )
                : new ApplicationShutdownEvent ( source, message );

        // then
        assertThat ( event.getSource ( ) ).isEqualTo ( source );
        assertThat ( event.getMessage ( ) ).isEqualTo ( message );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "service stopped", true ),
                Arguments.of ( "service stopped", false ),
                Arguments.of ( null, false )
        );
    }
}
