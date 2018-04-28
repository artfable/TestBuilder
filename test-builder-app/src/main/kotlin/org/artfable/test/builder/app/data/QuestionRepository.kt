package org.artfable.test.builder.app.data

import org.artfable.test.builder.app.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author artfable
 * 28.04.18
 */
@Repository
@Transactional
interface QuestionRepository : JpaRepository<Question, Long> {
}