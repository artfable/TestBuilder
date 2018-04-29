package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.QuestionGroupRepository;
import org.artfable.test.builder.app.data.QuestionRepository;
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

    @Autowired
    private QuestionGroupRepository questionGroupRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTest(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test with id [" + id + "] isn't exist"));
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
}
