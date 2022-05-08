package com.github.yusufugurozbek.mcq.repository

import com.github.yusufugurozbek.mcq.data.QuestionEntity
import org.springframework.data.repository.CrudRepository

interface QuestionRepository : CrudRepository<QuestionEntity, Long> {
    fun findAllByCourseId(courseId: Long): List<QuestionEntity>
}
