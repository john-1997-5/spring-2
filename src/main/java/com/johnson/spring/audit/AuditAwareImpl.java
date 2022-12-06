package com.johnson.spring.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String loggedName = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return Optional.ofNullable(loggedName);
    }
}
