package io.github.ninobomba.utils.spring.i18n;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class I18nFieldsErrorsSupportTest {

	@Mock
	private BindingResult bindingResult;

	@Mock
	private MessageSource messageSource;

	@Test
	void build_shouldReturnFormattedErrors ( ) {
		FieldError error = new FieldError ( "object", "field1", "rejected", false, new String[] { "code1" }, null, "default message" );
		when ( bindingResult.getFieldErrors ( ) ).thenReturn ( List.of ( error ) );

		String result = I18nFieldsErrorsSupport.build ( bindingResult );

		assertTrue ( result.contains ( "Field Name : [field1]" ) );
		assertTrue ( result.contains ( "Rejected Value : [rejected]" ) );
		assertTrue ( result.contains ( "Code : [code1]" ) );
		assertTrue ( result.contains ( "Error : [default message]" ) );
	}

	@Test
	void getMessage_shouldReturnMessageFromSource ( ) {
		String code = "test.code";
		String defaultMsg = "default";
		Locale locale = Locale.ENGLISH;
		when ( messageSource.getMessage ( code, null, "-> " + defaultMsg, locale ) ).thenReturn ( "translated message" );

		String result = I18nFieldsErrorsSupport.getMessage ( messageSource, code, defaultMsg, locale );

		assertEquals ( "translated message", result );
	}
}
