package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.TestRepository;
import org.artfable.test.builder.app.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

    public Test updateTest(Test test) {
        if (test.getId() == null) {
            throw new IllegalArgumentException("Id should be specified to modify the Test");
        }

        Test oldTest = testRepository.findById(test.getId())
                .orElseThrow(() -> new IllegalArgumentException("Test with id [" + test.getId() + "] isn't exist"));

        oldTest.setName(test.getName());
        oldTest.setComment(test.getComment());
        oldTest.setDescription(test.getDescription());
        return testRepository.saveAndFlush(oldTest);
    }

//    @Transactional
//    public List<QuestionGroup> getQuestionGroups(Long testId) {
//        Optional<Test> optionalTest = testRepository.findById(testId);
//        return optionalTest.orElseThrow(() -> new IllegalArgumentException("No test with id [" + testId + "]")).getQuestionGroups();
//    }
}
