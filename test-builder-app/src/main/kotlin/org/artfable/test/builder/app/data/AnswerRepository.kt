package org.artfable.test.builder.app.data

import org.artfable.test.builder.app.model.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author artfable
 * 26.05.18
 */
@Repository
@Transactional
interface AnswerRepository: JpaRepository<Answer, Long> {
}