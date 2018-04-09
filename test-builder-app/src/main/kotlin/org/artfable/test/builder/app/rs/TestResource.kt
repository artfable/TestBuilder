package org.artfable.test.builder.app.rs

import org.artfable.test.builder.app.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author artfable
 * 02.04.18
 */
@RestController
@RequestMapping("/api/tests")
class TestResource {

    @Autowired
    private lateinit var testService: TestService

    @RequestMapping(method = [RequestMethod.GET])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAllTests(): ResponseEntity<Any> {
        return ResponseEntity.ok(testService.allTests)
    }
}