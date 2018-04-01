package org.artfable.test.builder.app.model

import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "ANSWERS")
data class Answer(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
        val text: String = "",
        val correct: Boolean = false
)