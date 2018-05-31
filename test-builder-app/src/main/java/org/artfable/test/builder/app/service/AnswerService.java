package org.artfable.test.builder.app.service;

import org.artfable.test.builder.app.data.AnswerRepository;
import org.artfable.test.builder.app.data.QuestionRepository;
import org.artfable.test.builder.app.model.Answer;
import org.artfable.test.builder.app.model.Question;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author artfable
 * 26.05.18
 */
@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Transactional
    public Set<Answer> getAnswers(long testId, long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question with id " + questionId + " doesn't exist"));

        return question.getAnswers();
    }

    @Transactional
    public Answer createAnswer(long testId, long questionId, Answer answer) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question with id " + questionId + " doesn't exist"));

        if (answer.getId() != null) {
            throw new IllegalArgumentException("Id should be null for a new answer!");
        }

        answer.setQuestion(question);
        question.getAnswers().add(answer);
        return answerRepository.saveAndFlush(answer);
    }

    @Transactional
    public Answer updateAnswer(long testId, long questionId, long answerId, Answer answer) {
        if (answer == null || answer.getId() == null || answer.getId() != answerId) {
            throw new IllegalArgumentException("AnswerId doesn't match answer");
        }

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question with id " + questionId + " doesn't exist"));
        Answer oldAnswer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("Answer with id " + answerId + " doesn't exist"));

        oldAnswer.setCorrect(answer.getCorrect());
        return answerRepository.saveAndFlush(answer);
    }

    @Transactional
    public void deleteAnswer(long testId, long questionId, long answerId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("Question with id " + questionId + " doesn't exist"));
        boolean exist = question.getAnswers().stream().anyMatch(answer -> answer.getId() != answerId);

        if (!exist) {
            throw new IllegalArgumentException("Answer with id " + answerId + " doesn't exist in question " + questionId);
        }

        answerRepository.deleteById(answerId);
    }
}
