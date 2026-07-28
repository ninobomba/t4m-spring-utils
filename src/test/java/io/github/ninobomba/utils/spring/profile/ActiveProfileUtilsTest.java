package io.github.ninobomba.utils.spring.profile;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ActiveProfileUtilsTest {

    @Test
    void testIsProductionProfileActive ( ) {
        assertThat ( ActiveProfileUtils.isProductionProfileActive ( "prod" ) ).isTrue ( );
        assertThat ( ActiveProfileUtils.isProductionProfileActive ( "production" ) ).isTrue ( );
        assertThat ( ActiveProfileUtils.isProductionProfileActive ( "PROD" ) ).isTrue ( );
        assertThat ( ActiveProfileUtils.isProductionProfileActive ( "  prod  " ) ).isTrue ( );
        assertThat ( ActiveProfileUtils.isProductionProfileActive ( "dev" ) ).isFalse ( );
        assertThat ( ActiveProfileUtils.isProductionProfileActive ( null ) ).isFalse ( );
    }

    @Test
    void testResolvedProductionProfile ( ) {
        String[] environment = { "dev", "prod" };
        assertThat ( ActiveProfileUtils.resolvedProductionProfile ( "dev", environment ) ).isEqualTo ( "production" );

        String[] devEnv = { "dev", "test" };
        assertThat ( ActiveProfileUtils.resolvedProductionProfile ( "dev", devEnv ) ).isEqualTo ( "dev" );

        String[] untrimmedEnv = { " prod " };
        assertThat ( ActiveProfileUtils.resolvedProductionProfile ( "dev", untrimmedEnv ) ).isEqualTo ( "production" );
    }

    @Test
    void testResolvedProductionProfileWithNullEnvironment ( ) {
        assertThat ( ActiveProfileUtils.resolvedProductionProfile ( "dev", null ) ).isEqualTo ( "dev" );
    }

    @Test
    void testGetDetailedMessage ( ) {
        String message = "Custom error";
        assertThat ( ActiveProfileUtils.getDetailedMessage ( message, "dev" ) ).isEqualTo ( message );
        assertThat ( ActiveProfileUtils.getDetailedMessage ( message, "prod" ) ).isEqualTo ( "Contact the administrator for more information" );
    }
}
