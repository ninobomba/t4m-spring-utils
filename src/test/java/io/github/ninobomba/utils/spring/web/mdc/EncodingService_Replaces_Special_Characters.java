package io.github.ninobomba.utils.spring.web.mdc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.github.ninobomba.utils.spring.web.mdc.EncodingService.Type.REPLACE_SPECIAL_CHARACTERS;
import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `EncodingService` replaces special characters for sanitizer mode. */
class EncodingService_Replaces_Special_Characters {

    @DisplayName ( "EncodingService replaces risky characters with blanks" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String input, String expected ) {
        // given
        var value = input;

        // when
        var actual = EncodingService.INSTANCE.encode ( value, REPLACE_SPECIAL_CHARACTERS );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "abc", "abc" ),
                Arguments.of ( "a<b>c", "a b c" ),
                Arguments.of ( "x${y}", "x  y " )
        );
    }
}
