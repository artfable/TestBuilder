package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.QuestionGroupRepository;
import org.artfable.test.builder.app.data.TestRepository;
import org.artfable.test.builder.app.model.QuestionGroup;
import org.artfable.test.builder.app.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author artfable
 * 28.04.18
 */
@Service
public class QuestionGroupService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionGroupRepository questionGroupRepository;

    @Transactional
    public List<QuestionGroup> getQuestionGroups(Long testId) {
        return testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]"))
                .getQuestionGroups();
    }

    @Transactional
    public QuestionGroup createQuestionGroup(long testId, QuestionGroup questionGroup) {
        if (questionGroup.getId() != null) {
            throw new IllegalArgumentException("Id shouldn't be specified for a new group");
        }
        Test test = testRepository.findById(testId).orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]"));

        questionGroup.setTest(test);
        test.getQuestionGroups().add(questionGroup);
        return questionGroupRepository.saveAndFlush(questionGroup);
    }
}
