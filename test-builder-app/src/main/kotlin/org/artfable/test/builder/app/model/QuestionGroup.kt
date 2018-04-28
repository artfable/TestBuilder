package org.artfable.test.builder.app.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "QUESTION_GROUPS")
data class QuestionGroup(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val name: String = "",
        val description: String = "",
        @Column(name = "QUESTION_AMOUNT") val questionAmount: Int = 0,
        val points: Int = 0,
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")
        val questions: Set<Question> = HashSet()
) {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "TEST_ID")
    var test: Test? = null

    @Transient
    fun chooseRandomQuestions(): List<Question> {
        if (questionAmount >= questions.size) {
            return ArrayList(questions)
        }
        val random = Random()
        val buffer = ArrayList(questions)

        for (i in questions.size leftOpenDownTo questionAmount) {
            buffer.removeAt(random.nextInt(i))
        }

        return buffer
    }
}

infix fun Int.leftOpenDownTo(to: Int): IntProgression = IntProgression.fromClosedRange(this - 1, to, -1)