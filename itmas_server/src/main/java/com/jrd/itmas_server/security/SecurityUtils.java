package com.jrd.itmas_server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by jakub on 11.05.16.
 */
public final class SecurityUtils {
    private SecurityUtils() {
    }

    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                return (User)authentication.getPrincipal();
            }
        }

        throw new IllegalStateException("User not found!");
    }
}
