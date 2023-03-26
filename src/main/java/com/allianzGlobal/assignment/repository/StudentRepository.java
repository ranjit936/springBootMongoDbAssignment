package com.allianzGlobal.assignment.repository;

import com.allianzGlobal.assignment.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("StudentRepository")
public interface StudentRepository extends MongoRepository<Student, Integer> {

    List<Student> findByMarksLessThan(int marks);
}
