package com.github.yusufugurozbek.mcq.api

import com.github.yusufugurozbek.mcq.data.QuestionEntity
import com.github.yusufugurozbek.mcq.dto.QuestionDto
import com.github.yusufugurozbek.mcq.dto.ResultDto
import com.github.yusufugurozbek.mcq.dto.StudentAnswerDto

interface QuestionService {
    fun getQuestions(
        courseId: Long,
        studentId: Long,
        numberOfCorrectAnswersToMarkQuestionsAsMemorized: Int,
        withCorrectAnswers: Boolean
    ): List<QuestionDto>

    fun saveQuestions(questions: List<QuestionEntity>): List<QuestionEntity>
    fun answerQuestion(studentId: Long, studentAnswer: StudentAnswerDto): ResultDto
    fun answerQuestions(studentId: Long, studentAnswers: List<StudentAnswerDto>): List<ResultDto>
}
