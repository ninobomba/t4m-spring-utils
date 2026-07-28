package io.github.ninobomba.utils.spring.web.agent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `UADevice` record keeps all component values. */
class UADevice_Stores_Record_Values {

    @DisplayName ( "UADevice keeps constructor values in accessors" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test (
            String id,
            String token,
            String username,
            String timezone,
            String remoteHost,
            String userAgent,
            String type,
            String name,
            String category,
            String osProducer,
            String osName,
            String osVersion,
            String osVersionExtension
    ) {
        // given
        var device = new UADevice (
                id,
                token,
                username,
                timezone,
                remoteHost,
                userAgent,
                type,
                name,
                category,
                osProducer,
                osName,
                osVersion,
                osVersionExtension
        );

        // when
        var actualId = device.id ( );
        var actualToken = device.token ( );
        var actualUsername = device.username ( );

        // then
        assertThat ( actualId ).isEqualTo ( id );
        assertThat ( actualToken ).isEqualTo ( token );
        assertThat ( actualUsername ).isEqualTo ( username );
        assertThat ( device.timezone ( ) ).isEqualTo ( timezone );
        assertThat ( device.remoteHost ( ) ).isEqualTo ( remoteHost );
        assertThat ( device.userAgent ( ) ).isEqualTo ( userAgent );
        assertThat ( device.type ( ) ).isEqualTo ( type );
        assertThat ( device.name ( ) ).isEqualTo ( name );
        assertThat ( device.category ( ) ).isEqualTo ( category );
        assertThat ( device.osProducer ( ) ).isEqualTo ( osProducer );
        assertThat ( device.osName ( ) ).isEqualTo ( osName );
        assertThat ( device.osVersion ( ) ).isEqualTo ( osVersion );
        assertThat ( device.osVersionExtension ( ) ).isEqualTo ( osVersionExtension );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "1", "tk", "user", "UTC", "127.0.0.1", "ua", "MOBILE", "Pixel", "PHONE", "Google", "Android", "14", "" ),
                Arguments.of ( null, null, null, null, null, null, null, null, null, null, null, null, null )
        );
    }
}
