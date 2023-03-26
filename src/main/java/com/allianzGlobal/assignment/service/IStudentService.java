package com.allianzGlobal.assignment.service;

import com.allianzGlobal.assignment.model.Student;

import java.util.List;
import java.util.Optional;


public interface IStudentService {

    String addStudent(Student student);
    Optional<Student> getStudentById(int studentId);
    Student  updateStudentById(int studentId, Student student);
    String deleteStudentById(int studentId);

    List<Student> findByMarksLessThan(int marks);

    List<String> findDistinctStudentName();
}
