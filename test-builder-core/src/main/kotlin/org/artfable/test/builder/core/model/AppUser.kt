package org.artfable.test.builder.core.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

/**
 * @author artfable
 * 18.12.17
 */
@Entity
@Table(name = "USERS")
data class AppUser(
        @Id @GeneratedValue(strategy=GenerationType.AUTO) val id: Long? = null,
        @Column val login: String = "",
        @get:JsonIgnore @set:JsonProperty(value = "password") @Column var password: String = "",
        @Column val name: String? = null,
        @Column val lastName: String? = null,
        @Column val patronymic: String? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        @JoinTable(name = "USERS_RIGHTS", joinColumns = [
            JoinColumn(name = "USER_ID")
        ])
        @Column(name = "RIGHT")
        val roles: List<String> = ArrayList()
)