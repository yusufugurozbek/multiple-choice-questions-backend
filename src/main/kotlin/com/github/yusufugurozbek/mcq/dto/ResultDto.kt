package com.github.yusufugurozbek.mcq.dto

data class ResultDto(
    val questionId: Long,
    val result: Boolean,
    val correctAnswer: String
)
