package io.github.ninobomba.utils.spring.api.response.record;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApiRecordResponse.Success` preserves constructor values. */
class ApiRecordResponse_Success_Preserves_Values {

    @DisplayName ( "ApiRecordResponse.Success keeps constructor values" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String id, String message, Object data ) {
        // given
        var response = new ApiRecordResponse.Success ( id, message, data );

        // when
        var actualId = response.id ( );
        var actualMessage = response.message ( );
        var actualData = response.data ( );

        // then
        assertThat ( actualId ).isEqualTo ( id );
        assertThat ( actualMessage ).isEqualTo ( message );
        assertThat ( actualData ).isEqualTo ( data );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "id", "ok", "payload" ),
                Arguments.of ( null, null, null )
        );
    }
}
