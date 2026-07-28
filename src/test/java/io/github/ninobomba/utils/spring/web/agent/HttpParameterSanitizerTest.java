package io.github.ninobomba.utils.spring.web.agent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HttpParameterSanitizerTest {

	@Test
	void sanitize_shouldRemoveSpecialCharacters ( ) {
		String input = "Hello\u0000 World\u0007";
		String expected = "Hello World";
		assertEquals ( expected, HttpParameterSanitizer.sanitize ( input ) );
	}

	@Test
	void sanitize_shouldReturnDefaultMessage_whenInputIsNull ( ) {
		assertNull ( HttpParameterSanitizer.sanitize ( null ) );
	}

	@Test
	void sanitize_shouldKeepAlphanumericAndSpaces ( ) {
		String input = "User 123";
		assertEquals ( "User 123", HttpParameterSanitizer.sanitize ( input ) );
	}
}
