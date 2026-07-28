package io.github.ninobomba.utils.spring.i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `I18nFieldsErrorsSupport` resolves localized messages with fallback. */
class I18nFieldsErrorsSupport_Returns_Localized_Message {

    @DisplayName ( "I18nFieldsErrorsSupport resolves i18n messages with fallback" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( String code, String defaultMessage, Locale locale, String registeredMessage, String expected ) {
        // given
        var messageSource = new StaticMessageSource ( );
        if ( registeredMessage != null ) {
            messageSource.addMessage ( code, locale, registeredMessage );
        }

        // when
        var actual = I18nFieldsErrorsSupport.getMessage ( messageSource, code, defaultMessage, locale );

        // then
        assertThat ( actual ).isEqualTo ( expected );
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( "error.required", "required", Locale.US, "field required", "field required" ),
                Arguments.of ( "error.missing", "missing", Locale.US, null, "-> missing" )
        );
    }
}
