package org.artfable.test.builder.core

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Separate config to resolve circle during beans creation
 *
 * @author artfable
 * 01.07.18
 */
@Configuration
class SecurityEnvironmentConfig {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}