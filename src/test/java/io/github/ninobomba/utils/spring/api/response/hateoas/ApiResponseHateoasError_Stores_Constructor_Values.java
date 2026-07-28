package io.github.ninobomba.utils.spring.api.response.hateoas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApiResponseHateoasError` keeps constructor values. */
class ApiResponseHateoasError_Stores_Constructor_Values {

    @DisplayName ( "ApiResponseHateoasError keeps constructor values" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String id, String field, String value, String message, String description ) {
        // given
        var response = new ApiResponseHateoasError ( id, field, value, message, description );

        // when
        var actualId = response.getId ( );
        var actualField = response.getField ( );
        var actualValue = response.getValue ( );
        var actualMessage = response.getMessage ( );
        var actualDescription = response.getDescription ( );

        // then
        assertThat ( actualId ).isEqualTo ( id );
        assertThat ( actualField ).isEqualTo ( field );
        assertThat ( actualValue ).isEqualTo ( value );
        assertThat ( actualMessage ).isEqualTo ( message );
        assertThat ( actualDescription ).isEqualTo ( description );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "1", "name", "bad", "failed", "validation" ),
                Arguments.of ( null, null, null, null, null )
        );
    }
}
