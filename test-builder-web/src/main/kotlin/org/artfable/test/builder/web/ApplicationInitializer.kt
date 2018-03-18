package org.artfable.test.builder.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author artfable
 * 16.12.17
 */

fun main(args: Array<String>) {
    SpringApplication.run(ApplicationInitializer::class.java, *args)
}

@SpringBootApplication
class ApplicationInitializer