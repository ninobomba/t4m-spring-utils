package io.github.ninobomba.utils.spring.api.response.id;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith ( MockitoExtension.class )
class RequestIdRetrieverTest {

	@Mock
	private ServletWebRequest webRequest;

	@Test
	void getRequestId_shouldReturnId_whenHeaderIsPresent ( ) {
		jakarta.servlet.http.HttpServletRequest jakartaRequest = org.mockito.Mockito.mock ( jakarta.servlet.http.HttpServletRequest.class );
		when ( webRequest.getRequest ( ) ).thenReturn ( jakartaRequest );
		when ( jakartaRequest.getAttribute ( "X_REQUEST_ID" ) ).thenReturn ( "req-123" );

		String requestId = RequestIdRetriever.getRequestId ( webRequest );

		assertEquals ( "req-123", requestId );
	}

	@Test
	void getRequestId_shouldReturnUnknown_whenHeaderIsMissing ( ) {
		jakarta.servlet.http.HttpServletRequest jakartaRequest = org.mockito.Mockito.mock ( jakarta.servlet.http.HttpServletRequest.class );
		when ( webRequest.getRequest ( ) ).thenReturn ( jakartaRequest );
		when ( jakartaRequest.getAttribute ( "X_REQUEST_ID" ) ).thenReturn ( null );

		String requestId = RequestIdRetriever.getRequestId ( webRequest );

		assertEquals ( "unknown", requestId );
	}
}
