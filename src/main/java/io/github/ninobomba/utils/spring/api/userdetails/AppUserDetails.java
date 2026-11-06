package io.github.ninobomba.utils.spring.api.userdetails;

import lombok.Builder;

@Builder(toBuilder = true)
public record AppUserDetails(
        String id,
        String email,
        String accountId
) {
    public AppUserDetails {
        if (email != null) {
            email = email.strip();
        }
    }

    public boolean isValid() {
        return email != null && !email.isBlank();
    }
}