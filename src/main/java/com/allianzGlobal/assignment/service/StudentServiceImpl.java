package com.allianzGlobal.assignment.service;

import com.allianzGlobal.assignment.model.Student;
import com.allianzGlobal.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student Roll No : " + student.getRollNo()+ " Name : " +student.getStudentName()+ " is saved successfully.";
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public  Optional<Student> getStudentById(int studentId) { return studentRepository.findById(studentId);
    }

    @Override
    public String deleteStudentById(int studentId) {
        boolean isDeleted = false;
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            isDeleted = true;
        }
        return "Student with Id:" +studentId+ "deleted Successfully." +isDeleted;
    }

    @Override
    public Student updateStudentById(int studentId, Student studentInfo) {
        Optional<Student> studentData = studentRepository.findById(studentId);
        Student _student = studentData.get();
        if (studentData.isPresent()) {
            _student.setStudentId(studentInfo.getStudentId());
            _student.setStudentName(studentInfo.getStudentName());
            _student.setGender(studentInfo.getGender());
            _student.setRollNo(studentInfo.getRollNo());
            _student.setMarks(studentInfo.getMarks());
            _student.setSubject(studentInfo.getSubject());
           }
        return studentRepository.save(_student);
    }


    @Override
    public List<Student> getAllStudentByLowMarks() {
       return  studentRepository.getAllStudentByLowMarks();
    }

  }