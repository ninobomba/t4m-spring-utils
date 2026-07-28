package io.github.ninobomba.utils.spring.i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/** Verifies `I18nFieldsErrorsSupport` builds concatenated field error messages. */
class I18nFieldsErrorsSupport_Builds_Field_Error_Message {

    @DisplayName ( "I18nFieldsErrorsSupport builds field error payload" )
    @ParameterizedTest
    @MethodSource ( "provider" )
    void test ( boolean includeSecondError, boolean expectSeparator ) {
        // given
        var bindingResult = new BeanPropertyBindingResult ( new Object ( ), "payload" );
        bindingResult.addError ( new FieldError ( "payload", "email", "bad@", false, new String[] { "Email" }, null, "invalid email" ) );
        if ( includeSecondError ) {
            bindingResult.addError ( new FieldError ( "payload", "name", "", false, new String[] { "NotBlank" }, null, "name is required" ) );
        }

        // when
        var actual = I18nFieldsErrorsSupport.build ( bindingResult );

        // then
        assertThat ( actual ).contains ( "Field Name : [email]" );
        assertThat ( actual ).contains ( "Error : [invalid email]" );
        if ( expectSeparator ) {
            assertThat ( actual ).contains ( " | " );
        } else {
            assertThat ( actual ).doesNotContain ( " | " );
        }
    }

    static Stream < Arguments > provider ( ) {
        return Stream.of (
                Arguments.of ( false, false ),
                Arguments.of ( true, true )
        );
    }
}
