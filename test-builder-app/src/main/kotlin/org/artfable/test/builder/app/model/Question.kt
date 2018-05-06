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
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val text: String = "",
        val comment: String = "",
        val type: Type = Type.SINGLE,
        @JsonIgnore
        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
        val answers: Set<Answer> = HashSet()
) {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    var questionGroup: QuestionGroup? = null

    enum class Type {
        MULTIPLE, SINGLE
    }
}
