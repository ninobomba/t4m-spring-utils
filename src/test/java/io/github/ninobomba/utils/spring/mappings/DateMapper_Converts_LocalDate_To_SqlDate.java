package io.github.ninobomba.utils.spring.mappings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `DateMapper` converts `LocalDate` to `java.sql.Date`. */
class DateMapper_Converts_LocalDate_To_SqlDate {

    @DisplayName ( "DateMapper converts LocalDate into sql date" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( LocalDate input, Date expected ) {
        // given
        var mapper = new DateMapper ( );

        // when
        var actual = mapper.localDateToSqlDate ( input );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, null ),
                Arguments.of ( LocalDate.of ( 1970, 1, 1 ), Date.valueOf ( "1970-01-01" ) ),
                Arguments.of ( LocalDate.of ( 2024, 2, 29 ), Date.valueOf ( "2024-02-29" ) )
        );
    }
}
