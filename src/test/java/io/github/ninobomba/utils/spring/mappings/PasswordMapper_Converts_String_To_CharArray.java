package io.github.ninobomba.utils.spring.mappings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `PasswordMapper` converts `String` into `char[]`. */
class PasswordMapper_Converts_String_To_CharArray {

    @DisplayName ( "PasswordMapper converts string to char array" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String input, char[] expected ) {
        // given
        var mapper = new PasswordMapper ( );

        // when
        var actual = mapper.map ( input );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null ),
                Arguments.of ( "", new char[] { } ),
                Arguments.of ( "pass", new char[] { 'p', 'a', 's', 's' } )
        );
    }
}
