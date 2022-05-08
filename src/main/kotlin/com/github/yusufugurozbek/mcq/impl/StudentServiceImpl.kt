package com.github.yusufugurozbek.mcq.impl

import com.github.yusufugurozbek.mcq.api.StudentService
import com.github.yusufugurozbek.mcq.data.StudentEntity
import com.github.yusufugurozbek.mcq.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(val repository: StudentRepository) : StudentService {
    override fun getStudents(): List<StudentEntity> = repository.findAll().toMutableList()

    override fun saveStudents(students: List<StudentEntity>): List<StudentEntity> {
        return repository.saveAll(students).toList()
    }
}
