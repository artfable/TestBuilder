package org.artfable.test.builder.app.model

import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "COMPLETED_TESTS")
class CompletedTest(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val userId: Long = 0,
        val testId: Long = 0,
        val startTime: LocalDateTime = LocalDateTime.now(),
        val points: Int = 0,
        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "COMPLETED_QUESTIONS", joinColumns = [JoinColumn(name = "TEST_ID")])
        val questions: List<CompletedQuestion> = ArrayList()
)