package com.github.yusufugurozbek.mcq.api

import com.github.yusufugurozbek.mcq.data.CourseEntity

interface CourseService {
    fun getCourses(): List<CourseEntity>
    fun saveCourses(courses: List<CourseEntity>): List<CourseEntity>
    fun resetCourse(courseId: Long, studentId: Long)
}
