package org.artfable.test.builder.app.model

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Table

/**
 * @author artfable
 * 21.03.18
 */
@Embeddable
@Table(name = "COMPLETED_QUESTIONS")
class CompletedQuestion(
        @Column(name = "QUESTION_ID") val questionId: Long = 0,
        @Column(name = "QUESTION_SNAPSHOT") val questionSnapshot: String = ""
//        val answerIds: Set<Long> = HashSet()
)