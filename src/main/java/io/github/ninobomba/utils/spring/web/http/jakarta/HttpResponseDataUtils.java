package io.github.ninobomba.utils.spring.web.http.jakarta;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * The HttpResponseDataUtils interface provides utility methods for working with response data in the context of HTTP.
 */
public interface HttpResponseDataUtils {
	/**
	 * Retrieves the response headers as a map of key-value pairs.
	 *
	 * @param servletResponse the response object from which to retrieve the headers
	 * @return a map containing the response headers, where the keys are header names and the values are header values
	 */
	static Map < String, String > getResponseHeadersMap ( ServletResponse servletResponse ) {
		return getResponseHeadersMap ( ( HttpServletResponse ) servletResponse );
	}

	/**
	 * Converts the response headers of an HttpServletResponse into a Map.
	 *
	 * @param httpResponse the HttpServletResponse object containing the response headers
	 * @return a Map representing the response headers, where the keys are the header names and the values are the header values
	 */
	static Map < String, String > getResponseHeadersMap ( HttpServletResponse httpResponse ) {
		return httpResponse.getHeaderNames ( ).stream ( ).collect ( toMap ( key -> key, httpResponse::getHeader ) );
	}

	static void addResponseHeader(ServletResponse servletResponse, String headerName, String headerValue) {
		if (servletResponse instanceof HttpServletResponse httpResponse) {
			httpResponse.addHeader(headerName, headerValue);
		} else {
			throw new IllegalArgumentException("Response is not an HttpServletResponse");
		}
	}

	static void addDateTimeToResponseHeader(ServletResponse servletResponse) {
		String formattedDate = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		addResponseHeader(servletResponse, "ResponseDateTime", formattedDate);
	}

	static void addElapseTimeToResponseHeader(ServletResponse servletResponse, LocalDateTime startTime) {
		long elapsedMillis = Duration.between(startTime, LocalDateTime.now()).toMillis();
		addResponseHeader(servletResponse, "ResponseElapseTime", elapsedMillis + " ms");
	}

}