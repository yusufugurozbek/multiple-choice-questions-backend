package com.github.yusufugurozbek.mcq.dto

data class QuestionDto(
    val id: Long?,
    val courseId: Long,
    val question: String,
    val answers: List<String>,
    val correctAnswer: String?
)
