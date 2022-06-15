package com.assignment.excel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.excel.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
