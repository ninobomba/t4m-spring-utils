package io.github.ninobomba.utils.spring.mappings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `DateMapper` converts `java.sql.Date` to `LocalDate`. */
class DateMapper_Converts_SqlDate_To_LocalDate {

    @DisplayName ( "DateMapper converts sql date into LocalDate" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( Date input, LocalDate expected ) {
        // given
        var mapper = new DateMapper ( );

        // when
        var actual = mapper.sqlDateToLocalDate ( input );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null ),
                Arguments.of ( Date.valueOf ( "1970-01-01" ), LocalDate.of ( 1970, 1, 1 ) ),
                Arguments.of ( Date.valueOf ( "2024-02-29" ), LocalDate.of ( 2024, 2, 29 ) )
        );
    }
}
