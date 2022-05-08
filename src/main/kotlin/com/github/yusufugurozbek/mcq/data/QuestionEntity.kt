package com.github.yusufugurozbek.mcq.data

import com.github.yusufugurozbek.mcq.dto.QuestionDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "question")
class QuestionEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var courseId: Long,
    var question: String,
    var correctAnswer: String,
    var answerOne: String,
    var answerTwo: String,
    var answerThree: String,
    var answerFour: String
) {
    fun toDto(withCorrectAnswers: Boolean) = QuestionDto(
        id = id,
        courseId = courseId,
        question = question,
        answers = listOf(correctAnswer, answerOne, answerTwo, answerThree, answerFour).shuffled(),
        correctAnswer = if(withCorrectAnswers) correctAnswer else null
    )
}
