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
		javax.servlet.http.HttpServletRequest javaxRequest = org.mockito.Mockito.mock ( javax.servlet.http.HttpServletRequest.class );
		when ( webRequest.getRequest ( ) ).thenReturn ( javaxRequest );
		when ( javaxRequest.getAttribute ( "X_REQUEST_ID" ) ).thenReturn ( "req-123" );

		String requestId = RequestIdRetriever.getRequestId ( webRequest );

		assertEquals ( "req-123", requestId );
	}

	@Test
	void getRequestId_shouldReturnUnknown_whenHeaderIsMissing ( ) {
		javax.servlet.http.HttpServletRequest javaxRequest = org.mockito.Mockito.mock ( javax.servlet.http.HttpServletRequest.class );
		when ( webRequest.getRequest ( ) ).thenReturn ( javaxRequest );
		when ( javaxRequest.getAttribute ( "X_REQUEST_ID" ) ).thenReturn ( null );

		String requestId = RequestIdRetriever.getRequestId ( webRequest );

		assertEquals ( "unknown", requestId );
	}
}
