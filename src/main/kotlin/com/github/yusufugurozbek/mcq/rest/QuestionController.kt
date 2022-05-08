package com.github.yusufugurozbek.mcq.rest

import com.github.yusufugurozbek.mcq.api.QuestionService
import com.github.yusufugurozbek.mcq.data.QuestionEntity
import com.github.yusufugurozbek.mcq.dto.ResultDto
import com.github.yusufugurozbek.mcq.dto.StudentAnswerDto
import com.github.yusufugurozbek.mcq.logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/question")
class QuestionController(val service: QuestionService) {
    val logger = logger()

    @PostMapping
    fun saveQuestion(@RequestBody questions: List<QuestionEntity>): List<QuestionEntity> {
        return service.saveQuestions(questions)
    }

    @PutMapping
    fun answerQuestions(@RequestParam(required = true) studentId: Long, @RequestBody studentAnswers: List<StudentAnswerDto>): List<ResultDto> {
        return service.answerQuestions(studentId, studentAnswers)
    }
}
