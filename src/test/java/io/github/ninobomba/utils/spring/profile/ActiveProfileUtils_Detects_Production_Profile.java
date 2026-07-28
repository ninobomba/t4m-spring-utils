package io.github.ninobomba.utils.spring.profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ActiveProfileUtils` identifies production profiles. */
class ActiveProfileUtils_Detects_Production_Profile {

    @DisplayName ( "ActiveProfileUtils identifies prod profiles with trimming" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String profile, boolean expected ) {
        // given
        var activeProfile = profile;

        // when
        var actual = ActiveProfileUtils.isProductionProfileActive ( activeProfile );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( null, false ),
                Arguments.of ( "dev", false ),
                Arguments.of ( "prod", true ),
                Arguments.of ( "production", true ),
                Arguments.of ( " PROD ", true )
        );
    }
}
