package io.github.ninobomba.utils.spring.api.response.record;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApiRecordResponse.Error` applies defaults for null values. */
class ApiRecordResponse_Error_Defaults_Null_Values {

    @DisplayName ( "ApiRecordResponse.Error applies defaults for nullable fields" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test (
            String id,
            String field,
            String value,
            String message,
            String description,
            String expectedId,
            String expectedField,
            String expectedValue,
            String expectedMessage,
            String expectedDescription
    ) {
        // given
        var response = new ApiRecordResponse.Error ( id, field, value, message, description );

        // when
        var actualId = response.id ( );

        // then
        assertThat ( actualId ).isEqualTo ( expectedId );
        assertThat ( response.field ( ) ).isEqualTo ( expectedField );
        assertThat ( response.value ( ) ).isEqualTo ( expectedValue );
        assertThat ( response.message ( ) ).isEqualTo ( expectedMessage );
        assertThat ( response.description ( ) ).isEqualTo ( expectedDescription );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null, null, null, null, "", null, null, ApiRecordResponse.Messages.FAILED_MESSAGE, null ),
                Arguments.of ( "id", "name", "bad", "custom", "details", "id", "name", "bad", "custom", "details" )
        );
    }
}
