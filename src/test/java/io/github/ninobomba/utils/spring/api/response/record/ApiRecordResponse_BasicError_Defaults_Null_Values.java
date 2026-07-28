package io.github.ninobomba.utils.spring.api.response.record;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApiRecordResponse.BasicError` applies defaults for null values. */
class ApiRecordResponse_BasicError_Defaults_Null_Values {

    @DisplayName ( "ApiRecordResponse.BasicError applies default id and message" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String id, String message, String expectedId, String expectedMessage ) {
        // given
        var response = new ApiRecordResponse.BasicError ( id, message );

        // when
        var actualId = response.id ( );
        var actualMessage = response.message ( );

        // then
        assertThat ( actualId ).isEqualTo ( expectedId );
        assertThat ( actualMessage ).isEqualTo ( expectedMessage );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null, "", ApiRecordResponse.Messages.FAILED_MESSAGE ),
                Arguments.of ( "id-1", "custom", "id-1", "custom" )
        );
    }
}
