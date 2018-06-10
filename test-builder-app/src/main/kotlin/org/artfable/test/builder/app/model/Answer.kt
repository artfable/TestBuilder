package org.artfable.test.builder.app.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "ANSWERS")
data class Answer(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val text: String = "",
        var correct: Boolean = false
) {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "QUESTION_ID")
    var question: Question? = null
}