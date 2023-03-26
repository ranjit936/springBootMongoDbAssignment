package com.allianzGlobal.assignment.service;

import com.allianzGlobal.assignment.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IStudentService {

    String addStudent(Student student);
    Optional<Student> getStudentById(int studentId);
    List<Student> getAllStudent();
    String deleteStudentById(int studentId);
    Student  updateStudentById(int studentId, Student student);
    List<Student> getAllStudentByLowMarks();
}
