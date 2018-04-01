package org.artfable.test.builder.app.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

/**
 * @author artfable
 * 26.03.18
 */
@ExtendWith(MockitoExtension::class)
internal class QuestionGroupTest {

    @Test
    fun chooseRandomQuestions() {
        val questionAmount = 5
        val questions: MutableSet<Question> = HashSet()
        for (i in 0..9) {
            questions.add(Question(i.toLong()))
        }
        val result = QuestionGroup(questionAmount = questionAmount, questions = questions).chooseRandomQuestions()

        assertEquals(questionAmount, result.size)
        result.forEach { question ->
            assertNotNull(question.id)
            assertTrue(question.id!! in 0..9)
        }
    }

    @Test
    fun chooseRandomQuestions_empty() {
        assertTrue(QuestionGroup().chooseRandomQuestions().isEmpty())
    }
}