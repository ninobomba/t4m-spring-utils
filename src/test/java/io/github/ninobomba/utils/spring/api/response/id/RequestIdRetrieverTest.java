package io.github.ninobomba.utils.spring.api.response.id;

import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.ServletWebRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestIdRetrieverTest {

	@Test
	void getRequestId_shouldReturnId_whenHeaderIsPresent ( ) {
		HttpServletRequest jakartaRequest = mock ( HttpServletRequest.class );
		HttpServletResponse jakartaResponse = mock ( HttpServletResponse.class );
		ServletWebRequest webRequest = new ServletWebRequest ( jakartaRequest, jakartaResponse );
		when ( jakartaRequest.getAttribute ( "X_REQUEST_ID" ) ).thenReturn ( "req-123" );

		String requestId = RequestIdRetriever.getRequestId ( webRequest );

		assertEquals ( "req-123", requestId );
	}

	@Test
	void getRequestId_shouldReturnUnknown_whenHeaderIsMissing ( ) {
		HttpServletRequest jakartaRequest = mock ( HttpServletRequest.class );
		HttpServletResponse jakartaResponse = mock ( HttpServletResponse.class );
		ServletWebRequest webRequest = new ServletWebRequest ( jakartaRequest, jakartaResponse );
		when ( jakartaRequest.getAttribute ( "X_REQUEST_ID" ) ).thenReturn ( null );

		String requestId = RequestIdRetriever.getRequestId ( webRequest );

		assertEquals ( "unknown", requestId );
	}
}
