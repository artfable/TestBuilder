package org.artfable.test.builder.app.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "QUESTIONS")
data class Question(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
        val text: String = "",
        val comment: String = "",
        val type: Type = Type.SINGLE,
        @JsonIgnore
        @OneToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "ANSWERS", joinColumns = [JoinColumn(name = "QUESTION_ID")])
        val answers: Set<Answer> = HashSet()
) {

    enum class Type {
        MULTIPLE, SINGLE
    }
}
