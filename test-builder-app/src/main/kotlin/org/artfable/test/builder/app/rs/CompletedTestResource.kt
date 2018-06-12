package org.artfable.test.builder.app.rs

import org.artfable.test.builder.app.service.CompletedTestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author artfable
 * 10.06.18
 */
@RestController
@RequestMapping(path = ["/api/tests/completed"])
class CompletedTestResource {

    @Autowired
    private lateinit var completedTestService: CompletedTestService

    @RequestMapping(method = [RequestMethod.GET], path = ["/print"])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getTestForPrint(@RequestParam("testId") testId: Long): ResponseEntity<Any?> {
        return ResponseEntity.ok(completedTestService.getNewCompletedTest(testId))
    }
}