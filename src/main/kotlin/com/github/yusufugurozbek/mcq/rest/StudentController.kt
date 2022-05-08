package com.github.yusufugurozbek.mcq.rest

import com.github.yusufugurozbek.mcq.api.StudentService
import com.github.yusufugurozbek.mcq.data.StudentEntity
import com.github.yusufugurozbek.mcq.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/student")
class StudentController(val service: StudentService) {
    val logger = logger()

    @GetMapping
    fun getStudents(): List<StudentEntity> {
        val students = service.getStudents()
        logger.debug("Students: {}", students)
        return students
    }

    @PostMapping
    fun saveStudents(@RequestBody students: List<StudentEntity>): List<StudentEntity> {
        return service.saveStudents(students)
    }
}
