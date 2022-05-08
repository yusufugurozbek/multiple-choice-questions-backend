package com.github.yusufugurozbek.mcq.repository

import com.github.yusufugurozbek.mcq.data.StudentEntity
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<StudentEntity, Long>
