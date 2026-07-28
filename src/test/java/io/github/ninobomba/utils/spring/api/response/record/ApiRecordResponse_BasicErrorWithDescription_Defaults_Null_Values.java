package io.github.ninobomba.utils.spring.api.response.record;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApiRecordResponse.BasicErrorWithDescription` applies defaults for null values. */
class ApiRecordResponse_BasicErrorWithDescription_Defaults_Null_Values {

    @DisplayName ( "ApiRecordResponse.BasicErrorWithDescription applies defaults" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String id, String message, String description, String expectedId, String expectedMessage, String expectedDescription ) {
        // given
        var response = new ApiRecordResponse.BasicErrorWithDescription ( id, message, description );

        // when
        var actualId = response.id ( );
        var actualMessage = response.message ( );
        var actualDescription = response.description ( );

        // then
        assertThat ( actualId ).isEqualTo ( expectedId );
        assertThat ( actualMessage ).isEqualTo ( expectedMessage );
        assertThat ( actualDescription ).isEqualTo ( expectedDescription );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null, null, "", ApiRecordResponse.Messages.FAILED_MESSAGE, null ),
                Arguments.of ( "id-2", "custom", "details", "id-2", "custom", "details" )
        );
    }
}
