package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.TestRepository;
import org.artfable.test.builder.app.model.QuestionGroup;
import org.artfable.test.builder.app.model.Test;
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

//    @Transactional
//    public List<QuestionGroup> getQuestionGroups(Long testId) {
//        Optional<Test> optionalTest = testRepository.findById(testId);
//        return optionalTest.orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]")).getQuestionGroups();
//    }
}
