package org.artfable.test.builder.app.data

import org.artfable.test.builder.app.model.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author artfable
 * 02.04.18
 */
@Repository
@Transactional
interface TestRepository: JpaRepository<Test, Long> {
}