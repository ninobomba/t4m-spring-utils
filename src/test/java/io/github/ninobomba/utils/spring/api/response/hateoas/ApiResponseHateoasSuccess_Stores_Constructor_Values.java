package io.github.ninobomba.utils.spring.api.response.hateoas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ApiResponseHateoasSuccess` keeps constructor values. */
class ApiResponseHateoasSuccess_Stores_Constructor_Values {

    @DisplayName ( "ApiResponseHateoasSuccess keeps constructor values" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String id, String message, Object data ) {
        // given
        var response = new ApiResponseHateoasSuccess ( id, message, data );

        // when
        var actualId = response.getId ( );
        var actualMessage = response.getMessage ( );
        var actualData = response.getData ( );

        // then
        assertThat ( actualId ).isEqualTo ( id );
        assertThat ( actualMessage ).isEqualTo ( message );
        assertThat ( actualData ).isEqualTo ( data );
        assertThat ( response ).isInstanceOf ( java.io.Serializable.class );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "1", "ok", "payload" ),
                Arguments.of ( null, "accepted", 42 ),
                Arguments.of ( "x", null, null )
        );
    }
}
