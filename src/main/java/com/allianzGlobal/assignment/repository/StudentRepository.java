package com.allianzGlobal.assignment.repository;

import com.allianzGlobal.assignment.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, Integer> {
    @Query("{'name':?0 }")
    List<Student> findStudentByMarks();

    @Query("{'name':?0 }")
    List<Student> findStudentDistinctName();


    //get api to list all the students scored less than 35 in science
    //db.studentDetails.find({marks: {$lt:35}})

    //    @Query(value = "{'marks': {$lt:35}}")
    //    List<Student> findByLowMarks();


    //get unique student names
    //db.studentDetails.distinct('studentName')

    //   @Query(value = "{}")
    //  List<Student> findUniqueStudentNames();




}
