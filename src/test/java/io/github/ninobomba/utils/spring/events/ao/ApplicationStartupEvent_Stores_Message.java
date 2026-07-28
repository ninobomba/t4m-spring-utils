package io.github.ninobomba.utils.spring.events.ao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Clock;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApplicationStartupEvent` keeps message and source values. */
class ApplicationStartupEvent_Stores_Message {

    @DisplayName ( "ApplicationStartupEvent stores source and message" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String message, boolean withClock ) {
        // given
        var source = new Object ( );

        // when
        var event = withClock
                ? new ApplicationStartupEvent ( source, Clock.systemUTC ( ), message )
                : new ApplicationStartupEvent ( source, message );

        // then
        assertThat ( event.getSource ( ) ).isEqualTo ( source );
        assertThat ( event.getMessage ( ) ).isEqualTo ( message );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "service started", true ),
                Arguments.of ( "service started", false ),
                Arguments.of ( null, false )
        );
    }
}
