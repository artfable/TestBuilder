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
        val testId: Long? = null,
        val nameSnapshot: String = "",
        val startTime: LocalDateTime = LocalDateTime.now(),
        var points: Int = 0,
        @OneToMany(fetch = FetchType.EAGER)
        @JoinColumn(name = "TEST_ID", referencedColumnName = "ID")
        val questions: List<CompletedQuestion> = ArrayList()
) {
        constructor(testId: Long, nameSnapshot: String): this(testId = testId, nameSnapshot = nameSnapshot, id = null)
}