package com.prj.lms.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean // 등록자와 수정자를 처리해주는 AuditorAware 빈 등록
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
