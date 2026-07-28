package io.github.ninobomba.utils.spring.web.mdc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `EncodingService` returns blank values unchanged for all modes. */
class EncodingService_Returns_Blank_Values_Unchanged {

    @DisplayName ( "EncodingService leaves blank values unchanged" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String input, EncodingService.Type type ) {
        // given
        var value = input;

        // when
        var actual = EncodingService.INSTANCE.encode ( value, type );

        // then
        assertThat ( actual ).isEqualTo ( value );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, EncodingService.Type.REPLACE_SPECIAL_CHARACTERS ),
                Arguments.of ( "", EncodingService.Type.REPLACE_SPECIAL_CHARACTERS ),
                Arguments.of ( "   ", EncodingService.Type.SPRING )
        );
    }
}
