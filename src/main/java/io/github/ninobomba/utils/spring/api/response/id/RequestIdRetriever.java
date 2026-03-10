package io.github.ninobomba.utils.spring.api.response.id;

import org.springframework.web.context.request.ServletWebRequest;

import java.util.Optional;

public interface RequestIdRetriever {

    static String getRequestId(ServletWebRequest webRequest) {
        return Optional
                .of(webRequest.getRequest())
                .map(request -> request.getAttribute("X_REQUEST_ID"))
                .map(String::valueOf)
                .orElse("unknown");
    }

}
