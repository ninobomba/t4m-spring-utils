package io.github.ninobomba.utils.spring.api.userdetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
public final class AppUserDetailsRequestContext {

    public static final String ATTRIBUTE_EMAIL = "EMAIL";
    public static final String ATTRIBUTE_ACCOUNT_ID = "ACCOUNT_ID";

    private AppUserDetailsRequestContext() {
    }

    public static AppUserDetails getUserDetails(ServletWebRequest webRequest) {
        String email = null;
        String accountId = null;

        Object emailAttr = webRequest.getAttribute(ATTRIBUTE_EMAIL, ServletWebRequest.SCOPE_REQUEST);
        if (emailAttr instanceof String s) {
            email = s;
        }

        Object accountIdAttr = webRequest.getAttribute(ATTRIBUTE_ACCOUNT_ID, ServletWebRequest.SCOPE_REQUEST);
        if (accountIdAttr instanceof String s) {
            accountId = s;
        }

        return AppUserDetails.builder()
                .email(email)
                .accountId(accountId)
                .build();
    }
}