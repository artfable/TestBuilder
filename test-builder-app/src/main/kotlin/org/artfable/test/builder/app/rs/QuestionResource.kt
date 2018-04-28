package org.artfable.test.builder.app.rs

import org.artfable.test.builder.app.model.Question
import org.artfable.test.builder.app.service.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author artfable
 * 28.04.18
 */
@RestController
@RequestMapping(path = ["/api/tests/{testId}/questionGroups/{groupId}"])
class QuestionResource {

    @Autowired
    private lateinit var questionService: QuestionService

    @RequestMapping(method = [RequestMethod.POST])
    fun createQuestion(@PathVariable("groupId") groupId: Long, @RequestBody question: Question): ResponseEntity<Any?> {
        return ResponseEntity.ok(questionService.createQuestion(groupId, question))
    }
}