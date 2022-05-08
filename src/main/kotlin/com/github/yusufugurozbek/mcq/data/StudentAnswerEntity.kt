package com.github.yusufugurozbek.mcq.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "student_answer")
class StudentAnswerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var studentId: Long,
    @ManyToOne var question: QuestionEntity,
    var numberOfCorrectAnswers: Int
)
