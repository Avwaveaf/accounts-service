package com.avwaveaf.loanservice.audit;

import com.avwaveaf.loanservice.constants.BeansConst;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(BeansConst.AUDIT_AWARE_IMPL)
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Return current auditor of the App. for JPA fill modifiedBy and createdBy
     *
     * @return Optional<String> - name of auditor
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: Implement Spring Security for authorization mapping.
        return Optional.of("Guest");
    }

}
