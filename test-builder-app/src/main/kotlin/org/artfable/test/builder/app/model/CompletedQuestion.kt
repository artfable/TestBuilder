package org.artfable.test.builder.app.model

import javax.persistence.Embeddable
import javax.persistence.Table

/**
 * @author artfable
 * 21.03.18
 */
@Embeddable
@Table(name = "COMPLETED_QUESTIONS")
class CompletedQuestion(
    val questionId: Long = 0,
    val answerIds: Set<Long> = HashSet()
)