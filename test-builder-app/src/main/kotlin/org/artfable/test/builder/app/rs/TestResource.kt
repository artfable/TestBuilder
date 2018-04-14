package org.artfable.test.builder.app.rs

import org.artfable.test.builder.app.model.Test
import org.artfable.test.builder.app.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(method = [RequestMethod.POST])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createTest(@RequestBody test: Test): ResponseEntity<Any> {
        return ResponseEntity.ok(testService.createTest(test))
    }

    @RequestMapping(method = [RequestMethod.DELETE], path = ["/{id}"])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteTest(@PathVariable("id") id: Long): ResponseEntity<Any> {
        testService.deleteTest(id)
        return ResponseEntity.ok().build()
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/{id}"])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun updateTest(@RequestBody test: Test): ResponseEntity<Any> {
        return ResponseEntity.ok(testService.updateTest(test))
    }
}