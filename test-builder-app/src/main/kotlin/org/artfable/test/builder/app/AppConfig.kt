package org.artfable.test.builder.app

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * @author artfable
 * 21.03.18
 */
@ComponentScan
@EntityScan
@EnableJpaRepositories
class AppConfig