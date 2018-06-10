package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.QuestionGroupRepository;
import org.artfable.test.builder.app.data.TestRepository;
import org.artfable.test.builder.app.model.QuestionGroup;
import org.artfable.test.builder.app.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Transactional
    public QuestionGroup updateQuestionGroup(long testId, long groupId, QuestionGroup questionGroup) {
        if (questionGroup.getId() == null || questionGroup.getId() != groupId) {
            throw new IllegalArgumentException("groupId is incorrect");
        }
        Test test = testRepository.findById(testId).orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]"));
        QuestionGroup oldGroup = questionGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("No group with id [" + groupId + "]"));
        if (!oldGroup.getTest().equals(test)) {
            throw new IllegalArgumentException("Incorrect test for group");
        }

        questionGroup.setTest(test);
        questionGroup.getQuestions().clear();
        questionGroup.getQuestions().addAll(oldGroup.getQuestions());
        return questionGroupRepository.saveAndFlush(questionGroup);
    }

    @Transactional
    public void deleteQuestionGroup(long testId, long groupId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]"));
        if (test.getQuestionGroups().stream().noneMatch(questionGroup -> Objects.equals(questionGroup.getId(), groupId))) {
            throw new IllegalArgumentException("Test with id [" + testId + "] doesn't contains [" + groupId + "]");
        }

        questionGroupRepository.deleteById(groupId);
    }
}
