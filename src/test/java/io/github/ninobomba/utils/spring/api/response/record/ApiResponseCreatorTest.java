package io.github.ninobomba.utils.spring.api.response.record;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseCreatorTest {

	@Test
	void createSuccessResponse_shouldReturnOkResponse ( ) {
		String requestId = "req-123";
		String data = "some data";
		ResponseEntity < ApiRecordResponse > response = ApiResponseCreator.createSuccessResponse ( requestId, data, HttpStatus.OK );

		assertEquals ( HttpStatus.OK, response.getStatusCode ( ) );
		assertTrue ( response.getBody ( ) instanceof ApiRecordResponse.Success );
		ApiRecordResponse.Success success = ( ApiRecordResponse.Success ) response.getBody ( );
		assertEquals ( requestId, success.id ( ) );
		assertEquals ( data, success.data ( ) );
		assertEquals ( ApiRecordResponse.Messages.SUCCESS_MESSAGE, success.message ( ) );
	}

	@Test
	void createFailureResponse_shouldReturnErrorResponse ( ) {
		String requestId = "req-456";
		String message = "error occurred";
		ResponseEntity < ApiRecordResponse > response = ApiResponseCreator.createFailureResponse ( requestId, message, HttpStatus.BAD_REQUEST );

		assertEquals ( HttpStatus.BAD_REQUEST, response.getStatusCode ( ) );
		assertTrue ( response.getBody ( ) instanceof ApiRecordResponse.BasicError );
		ApiRecordResponse.BasicError error = ( ApiRecordResponse.BasicError ) response.getBody ( );
		assertEquals ( requestId, error.id ( ) );
		assertEquals ( message, error.message ( ) );
	}

	@Test
	void createFailureResponseWithDescription_shouldReturnErrorWithDescription ( ) {
		String requestId = "req-789";
		String message = "error occurred";
		String description = "detailed description";
		ResponseEntity < ApiRecordResponse > response = ApiResponseCreator.createFailureResponse ( requestId, message, description, HttpStatus.INTERNAL_SERVER_ERROR );

		assertEquals ( HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode ( ) );
		assertTrue ( response.getBody ( ) instanceof ApiRecordResponse.BasicErrorWithDescription );
		ApiRecordResponse.BasicErrorWithDescription error = ( ApiRecordResponse.BasicErrorWithDescription ) response.getBody ( );
		assertEquals ( requestId, error.id ( ) );
		assertEquals ( message, error.message ( ) );
		assertEquals ( description, error.description ( ) );
	}
}
