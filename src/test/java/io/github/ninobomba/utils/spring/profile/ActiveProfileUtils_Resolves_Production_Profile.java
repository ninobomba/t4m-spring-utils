package io.github.ninobomba.utils.spring.profile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `ActiveProfileUtils` resolves environment production aliases. */
class ActiveProfileUtils_Resolves_Production_Profile {

    @DisplayName ( "ActiveProfileUtils resolves production profile from environment" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String activeProfile, String[] environment, String expected ) {
        // given
        var selectedProfile = activeProfile;

        // when
        var actual = ActiveProfileUtils.resolvedProductionProfile ( selectedProfile, environment );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "dev", null, "dev" ),
                Arguments.of ( "dev", ( Object ) new String[] { "dev", "test" }, "dev" ),
                Arguments.of ( "dev", ( Object ) new String[] { "qa", "prod" }, "production" ),
                Arguments.of ( "dev", ( Object ) new String[] { " qa ", " production " }, "production" )
        );
    }
}
