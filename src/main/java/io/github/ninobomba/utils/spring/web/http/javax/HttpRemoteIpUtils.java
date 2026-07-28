package io.github.ninobomba.utils.spring.web.http.javax;

import com.google.common.net.InetAddresses;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import static com.google.common.net.HttpHeaders.X_FORWARDED_FOR;

/**
 * The HttpRemoteIpUtils class provides utilities for retrieving the remote IP address from HTTP request headers.
 */
public interface HttpRemoteIpUtils {

    Predicate < String > IP_ADDRESS_FORMAT_VALIDATOR = InetAddresses::isInetAddress;

    /**
     * Retrieves the remote IP address from HTTP request headers.
     *
     * @param request the HttpServletRequest containing the request headers
     * @return the remote IP address as a String, or null if no IP address is found in the headers
     */
    static String getRemoteIpByHttpRequestHeaders ( HttpServletRequest request ) {
        if ( Objects.isNull ( request ) ) return null;

        var remoteAddr = request.getRemoteAddr ( );

        if ( isTrustedProxyAddress ( remoteAddr ) ) {
            var xForwardedFor = request.getHeader ( X_FORWARDED_FOR );
            var forwardedIp = getFirstValidIpAddress ( xForwardedFor );

            if ( Objects.nonNull ( forwardedIp ) ) return forwardedIp;
        }

        return getFirstValidIpAddress ( remoteAddr );
    }

    private static String getFirstValidIpAddress ( String values ) {
        if ( StringUtils.isBlank ( values ) ) return null;

        return Arrays.stream ( values.split ( "," ) )
                .map ( String::trim )
                .filter ( StringUtils::isNotBlank )
                .filter ( IP_ADDRESS_FORMAT_VALIDATOR )
                .findFirst ( )
                .orElse ( null );
    }

    private static boolean isTrustedProxyAddress ( String address ) {
        var trustedProxy = getFirstValidIpAddress ( address );
        if ( Objects.isNull ( trustedProxy ) ) return false;

        var inetAddress = InetAddresses.forString ( trustedProxy );
        return inetAddress.isAnyLocalAddress ( )
                || inetAddress.isLoopbackAddress ( )
                || inetAddress.isSiteLocalAddress ( )
                || inetAddress.isLinkLocalAddress ( );
    }

}
