package org.artfable.test.builder.app.model

import javax.persistence.*

/**
 * @author artfable
 * 10.06.18
 */
@Entity
@Table(name = "ANSWERS")
data class CompletedAnswer(
        @Id val id: Long,
        @Column val text: String
)