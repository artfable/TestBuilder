package org.artfable.test.builder.app.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * @author artfable
 * 21.03.18
 */
@Entity
@Table(name = "TESTS")
data class Test(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        var name: String = "",
        var description: String = "",
        var comment: String = "",
        var duration: Long = 0,
        @JsonIgnore
        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "TEST_ID", referencedColumnName = "ID")
        val questionGroups: List<QuestionGroup> = ArrayList()
)