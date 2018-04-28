package org.artfable.test.builder.app.rs

import org.artfable.test.builder.app.model.QuestionGroup
import org.artfable.test.builder.app.service.QuestionGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author artfable
 * 23.04.18
 */
@RestController
@RequestMapping(path = ["/api/tests/{testId}/questionGroups"])
class QuestionGroupResource {

    @Autowired
    private lateinit var questionGroupService: QuestionGroupService

    @RequestMapping(method = [RequestMethod.GET])
    fun getQuestionGroups(@PathVariable("testId") testId: Long): ResponseEntity<Any?> {
        return ResponseEntity.ok(questionGroupService.getQuestionGroups(testId))
    }

    @RequestMapping(method = [RequestMethod.POST])
    fun createQuestionGroup(@PathVariable("testId") testId: Long, @RequestBody questionGroup: QuestionGroup): ResponseEntity<Any?> {
        return ResponseEntity.ok(questionGroupService.createQuestionGroup(testId, questionGroup))
    }
}