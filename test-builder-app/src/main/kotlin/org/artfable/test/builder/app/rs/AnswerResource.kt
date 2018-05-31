package org.artfable.test.builder.app.rs

import org.artfable.test.builder.app.model.Answer
import org.artfable.test.builder.app.model.Question
import org.artfable.test.builder.app.service.AnswerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/**
 * @author artfable
 * 23.05.18
 */
@RestController
@RequestMapping(path = ["/api/tests/{testId}/questionGroups/{groupId}/questions/{questionId}/answers"])
class AnswerResource {

    @Autowired
    private lateinit var answerService: AnswerService

    @RequestMapping(method = [RequestMethod.GET])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAnswers(@PathVariable("testId") testId: Long, @PathVariable("questionId") questionId: Long): ResponseEntity<Any?> {
        return ResponseEntity.ok(answerService.getAnswers(testId, questionId))
    }

    @RequestMapping(method = [RequestMethod.POST])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun createAnswer(@PathVariable("testId") testId: Long, @PathVariable("questionId") questionId: Long, @RequestBody answer: Answer): ResponseEntity<Any?> {
        return ResponseEntity.ok(answerService.createAnswer(testId, questionId, answer))
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/{id}"])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun updateAnswer(@PathVariable("testId") testId: Long, @PathVariable("questionId") questionId: Long, @PathVariable("id") answerId: Long, @RequestBody answer: Answer): ResponseEntity<Any?> {
        return ResponseEntity.ok(answerService.updateAnswer(testId, questionId, answerId, answer))
    }

    @RequestMapping(method = [RequestMethod.DELETE], path = ["/{id}"])
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteAnswer(@PathVariable("testId") testId: Long, @PathVariable("questionId") questionId: Long, @PathVariable("id") answerId: Long): ResponseEntity<Any?> {
        answerService.deleteAnswer(testId, questionId, answerId)
        return ResponseEntity.ok().build()
    }
}