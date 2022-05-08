package com.github.yusufugurozbek.mcq.repository

import com.github.yusufugurozbek.mcq.data.CourseEntity
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<CourseEntity, Long> {
    fun findAllByOrderByName(): List<CourseEntity>
}
