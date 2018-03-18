package org.artfable.test.builder.core.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * @author artfable
 * 26.12.17
 */
class UserDetailsDto(
        username: String,
        val id: Long,
        password: String?,
        enabled: Boolean,
        accountNonExpired: Boolean,
        credentialsNonExpired: Boolean,
        accountNonLocked: Boolean,
        authorities: MutableCollection<out GrantedAuthority>?
) : User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
    constructor(username: String, id: Long, password: String?, authorities: MutableCollection<out GrantedAuthority>?) :
            this(username, id, password, true, true, true, true, authorities)

}