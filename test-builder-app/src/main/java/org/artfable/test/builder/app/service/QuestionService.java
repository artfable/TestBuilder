package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.QuestionGroupRepository;
import org.artfable.test.builder.app.data.QuestionRepository;
import org.artfable.test.builder.app.model.Question;
import org.artfable.test.builder.app.model.QuestionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author artfable
 * 28.04.18
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionGroupRepository questionGroupRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public Question createQuestion(long groupId, Question question) {
        if (question.getId() != null) {
            throw new IllegalArgumentException("Id shouldn't be specified for a new question");
        }
        QuestionGroup questionGroup = questionGroupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("No group with id [" + groupId + "]"));

        question.setQuestionGroup(questionGroup);
        questionGroup.getQuestions().add(question);
        return questionRepository.saveAndFlush(question);
    }
}