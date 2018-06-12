package org.artfable.test.builder.app.model

import java.io.Serializable
import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "COMPLETED_QUESTIONS")
class CompletedQuestion(
        @Column(name = "TEST_ID") @Id val testId: Long? = null,
        @Column(name = "QUESTION_ID") @Id val questionId: Long = 0,
        @Column(name = "QUESTION_SNAPSHOT") val questionSnapshot: String = "",
        @Column(name = "GROUP_SNAPSHOT") val groupSnapshot: String = "",
        @Enumerated(EnumType.STRING)
        @Column(name = "TYPE_SNAPSHOT")
        val typeSnapshot: Question.Type = Question.Type.SINGLE,

        @ManyToMany
        val answers: Set<CompletedAnswer> = HashSet()
//        val answerIds: Set<Long> = HashSet()
) : Serializable