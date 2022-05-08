package com.github.yusufugurozbek.mcq.rest

import com.github.yusufugurozbek.mcq.api.CourseService
import com.github.yusufugurozbek.mcq.api.QuestionService
import com.github.yusufugurozbek.mcq.data.CourseEntity
import com.github.yusufugurozbek.mcq.dto.QuestionDto
import com.github.yusufugurozbek.mcq.logger
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Min


@Validated
@RestController
@RequestMapping("/api/course")
class CourseController(val courseService: CourseService, val questionService: QuestionService) {
    val logger = logger()

    @GetMapping
    fun getCourses(): List<CourseEntity> {
        val courses = courseService.getCourses()
        logger.debug("Courses: {}", courses)
        return courses
    }

    @PostMapping
    fun saveCourse(@RequestBody courses: List<CourseEntity>): List<CourseEntity> {
        return courseService.saveCourses(courses)
    }

    @GetMapping("/{courseId}/student/{studentId}")
    fun getQuestions(
        @PathVariable courseId: Long,
        @PathVariable studentId: Long,
        @Min(1)
        @RequestParam(
            required = false,
            defaultValue = "2"
        ) numberOfCorrectAnswersToMarkQuestionsAsMemorized: Int, @RequestParam(
            required = false,
            defaultValue = "true"
        ) withCorrectAnswers: Boolean
    ): List<QuestionDto> {
        val questions =
            questionService.getQuestions(
                courseId,
                studentId,
                numberOfCorrectAnswersToMarkQuestionsAsMemorized,
                withCorrectAnswers
            )
        logger.debug("Course id {}, questions: {}", courseId, questions)
        return questions
    }

    @DeleteMapping("/{courseId}/student/{studentId}")
    fun resetCourse(@PathVariable courseId: Long, @PathVariable studentId: Long) {
        courseService.resetCourse(courseId, studentId)
    }
}
