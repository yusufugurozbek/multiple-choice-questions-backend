package com.github.yusufugurozbek.mcq.impl

import com.github.yusufugurozbek.mcq.api.QuestionService
import com.github.yusufugurozbek.mcq.data.QuestionEntity
import com.github.yusufugurozbek.mcq.data.StudentAnswerEntity
import com.github.yusufugurozbek.mcq.dto.QuestionDto
import com.github.yusufugurozbek.mcq.dto.ResultDto
import com.github.yusufugurozbek.mcq.dto.StudentAnswerDto
import com.github.yusufugurozbek.mcq.exception.QuestionNotFoundException
import com.github.yusufugurozbek.mcq.repository.QuestionRepository
import com.github.yusufugurozbek.mcq.repository.StudentAnswerRepository
import org.springframework.stereotype.Service

@Service
class QuestionServiceImpl(
    val questionRepository: QuestionRepository,
    val studentAnswerRepository: StudentAnswerRepository
) : QuestionService {
    override fun getQuestions(
        courseId: Long,
        studentId: Long,
        numberOfCorrectAnswersToMarkQuestionsAsMemorized: Int,
        withCorrectAnswers: Boolean
    ): List<QuestionDto> {
        val questions = questionRepository.findAllByCourseId(courseId).map { it.toDto(withCorrectAnswers) }.shuffled()
        val memorizedQuestionIds =
            studentAnswerRepository.findAllByQuestionCourseIdAndStudentIdAndNumberOfCorrectAnswersGreaterThanEqual(
                courseId,
                studentId,
                numberOfCorrectAnswersToMarkQuestionsAsMemorized
            ).map { questionId -> questionId.questionId }
        return questions.filter { question -> !memorizedQuestionIds.contains(question.id) }
    }

    override fun saveQuestions(questions: List<QuestionEntity>): List<QuestionEntity> {
        return questionRepository.saveAll(questions).toList()
    }

    override fun answerQuestion(studentId: Long, studentAnswer: StudentAnswerDto): ResultDto {
        val question = questionRepository.findById(studentAnswer.questionId)
        if (question.isPresent) {
            val presentedQuestion = question.get()
            if (presentedQuestion.correctAnswer == studentAnswer.answer) {
                updateStudentAnswer(presentedQuestion, studentId, true)
                return ResultDto(studentAnswer.questionId, true, presentedQuestion.correctAnswer)
            }
            updateStudentAnswer(presentedQuestion, studentId, false)
            return ResultDto(studentAnswer.questionId, false, presentedQuestion.correctAnswer)
        }

        throw QuestionNotFoundException()
    }

    override fun answerQuestions(studentId: Long, studentAnswers: List<StudentAnswerDto>): List<ResultDto> {
        return studentAnswers.map { studentAnswer -> answerQuestion(studentId, studentAnswer) }
    }

    private fun updateStudentAnswer(question: QuestionEntity, studentId: Long, increase: Boolean) {
        val studentAnswer = studentAnswerRepository.findByQuestionIdAndStudentId(question.id!!, studentId)
        val newStudentAnswer = studentAnswer.map { presentedStudentAnswer ->
            StudentAnswerEntity(
                id = presentedStudentAnswer.id,
                studentId = presentedStudentAnswer.studentId,
                question = presentedStudentAnswer.question,
                numberOfCorrectAnswers = if (increase) presentedStudentAnswer.numberOfCorrectAnswers + 1 else presentedStudentAnswer.numberOfCorrectAnswers - 1
            )
        }.orElse(
            StudentAnswerEntity(
                studentId = studentId,
                question = question,
                numberOfCorrectAnswers = if (increase) 1 else -1
            )
        )
        studentAnswerRepository.save(newStudentAnswer)
    }
}
