package org.artfable.test.builder.core.data

import org.artfable.test.builder.core.model.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author artfable
 * 26.12.17
 */
@Repository
@Transactional
interface UserRepository: JpaRepository<AppUser, Long> {

    fun findByLoginAndPassword(login: String, password: String): AppUser?

    fun findByLogin(login: String): AppUser?
}