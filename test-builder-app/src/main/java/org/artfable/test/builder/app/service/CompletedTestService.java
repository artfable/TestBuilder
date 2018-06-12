package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.TestRepository;
import org.artfable.test.builder.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author artfable
 * 10.06.18
 */
@Service
public class CompletedTestService {

    @Autowired
    private TestRepository testRepository;

    @Transactional
    public CompletedTest getNewCompletedTest(long testId) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]"));

        CompletedTest completedTest = new CompletedTest(testId, test.getName());
        test.getQuestionGroups().forEach(questionGroup -> {
            List<CompletedQuestion> completedQuestions = questionGroup.chooseRandomQuestions().stream()
                    .map(question -> {
                        CompletedQuestion completedQuestion = new CompletedQuestion(completedTest.getId(),
                                question.getId(),
                                question.getText(),
                                questionGroup.getName(),
                                question.getType(),
                                new HashSet<>());
                        Set<CompletedAnswer> completedAnswers = question.getAnswers().stream()
                                .map(answer -> new CompletedAnswer(answer.getId(), answer.getText()))
                                .collect(Collectors.toSet());
                        completedQuestion.getAnswers().addAll(completedAnswers);
                        return completedQuestion;
                    })
                    .collect(Collectors.toList());
            completedTest.getQuestions().addAll(completedQuestions);
        });

        return completedTest;
    }
}
