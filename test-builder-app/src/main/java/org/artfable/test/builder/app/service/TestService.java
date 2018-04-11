package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.TestRepository;
import org.artfable.test.builder.app.model.QuestionGroup;
import org.artfable.test.builder.app.model.Test;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author artfable
 * 02.04.18
 */
@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test createTest(Test test) {
        if (test.getId() != null) {
            throw new IllegalArgumentException("There should be no id for a new Test");
        }

        return testRepository.saveAndFlush(test);
    }

//    @Transactional
//    public List<QuestionGroup> getQuestionGroups(Long testId) {
//        Optional<Test> optionalTest = testRepository.findById(testId);
//        return optionalTest.orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]")).getQuestionGroups();
//    }
}
