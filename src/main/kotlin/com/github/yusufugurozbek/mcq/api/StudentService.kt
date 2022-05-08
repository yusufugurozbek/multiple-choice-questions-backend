package com.github.yusufugurozbek.mcq.api

import com.github.yusufugurozbek.mcq.data.StudentEntity

interface StudentService {
    fun getStudents(): List<StudentEntity>
    fun saveStudents(students: List<StudentEntity>): List<StudentEntity>
}
