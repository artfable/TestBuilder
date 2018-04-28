package org.artfable.test.builder.app.data

import org.artfable.test.builder.app.model.QuestionGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author artfable
 * 23.04.18
 */
@Repository
@Transactional
interface QuestionGroupRepository : JpaRepository<QuestionGroup, Long> {
}