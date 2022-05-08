package com.github.yusufugurozbek.mcq.repository

import com.github.yusufugurozbek.mcq.data.QuestionId
import com.github.yusufugurozbek.mcq.data.StudentAnswerEntity
import org.springframework.data.repository.CrudRepository
import java.util.Optional
import javax.transaction.Transactional

interface StudentAnswerRepository : CrudRepository<StudentAnswerEntity, Long> {
    fun findByQuestionIdAndStudentId(questionId: Long, studentId: Long): Optional<StudentAnswerEntity>
    fun findAllByQuestionCourseIdAndStudentIdAndNumberOfCorrectAnswersGreaterThanEqual(
        questionCourseId: Long,
        studentId: Long,
        correctAnswer: Int
    ): List<QuestionId>

    @Transactional
    fun deleteAllByQuestionCourseIdAndStudentId(questionCourseId: Long, studentId: Long)
}
