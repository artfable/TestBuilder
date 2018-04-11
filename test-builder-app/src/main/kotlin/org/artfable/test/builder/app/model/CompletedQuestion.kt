package org.artfable.test.builder.app.model

import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Embeddable
@Table(name = "COMPLETED_QUESTIONS")
class CompletedQuestion(
        @Column(name = "QUESTION_ID") val questionId: Long = 0
//        val answerIds: Set<Long> = HashSet()
)