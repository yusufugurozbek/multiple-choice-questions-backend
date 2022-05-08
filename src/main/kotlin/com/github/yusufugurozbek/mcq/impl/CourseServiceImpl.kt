package com.github.yusufugurozbek.mcq.impl

import com.github.yusufugurozbek.mcq.api.CourseService
import com.github.yusufugurozbek.mcq.data.CourseEntity
import com.github.yusufugurozbek.mcq.repository.CourseRepository
import com.github.yusufugurozbek.mcq.repository.StudentAnswerRepository
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(val courseRepository: CourseRepository, val studentAnswerRepository: StudentAnswerRepository) :
    CourseService {
    override fun getCourses(): List<CourseEntity> = courseRepository.findAllByOrderByName()

    override fun saveCourses(courses: List<CourseEntity>): List<CourseEntity> {
        return courseRepository.saveAll(courses).toList()
    }

    override fun resetCourse(courseId: Long, studentId: Long) {
        studentAnswerRepository.deleteAllByQuestionCourseIdAndStudentId(courseId, studentId);
    }
}
