package io.github.ninobomba.utils.spring.profile;

public final class ActiveProfileUtils {


    private static final String PROFILE_PROD = "prod";
    private static final String PROFILE_PRODUCTION = "production";
    private static final String DEFAULT_ERROR_MESSAGE = "Contact the administrator for more information";

    private ActiveProfileUtils ( ) {
    }

    public static String getDetailedMessage ( String message, String activeProfile ) {
        return isProductionProfileActive ( activeProfile ) ? DEFAULT_ERROR_MESSAGE : message;
    }

    public static boolean isProductionProfileActive ( String activeProfile ) {
        if ( activeProfile == null ) {
            return false;
        }
        String normalized = activeProfile.trim ( );
        return PROFILE_PROD.equalsIgnoreCase ( normalized ) || PROFILE_PRODUCTION.equalsIgnoreCase ( normalized );
    }

    public static String resolvedProductionProfile ( String activeProfile, String[] environment ) {
        if ( environment == null ) {
            return activeProfile;
        }

        boolean prodActive = java.util.Arrays.stream ( environment )
                .anyMatch ( ActiveProfileUtils::isProductionProfileActive );

        return prodActive ? PROFILE_PRODUCTION : activeProfile;
    }

}