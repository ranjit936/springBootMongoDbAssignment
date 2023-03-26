package com.allianzGlobal.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.allianzGlobal.assignment.AssignmentApplication;
import com.allianzGlobal.assignment.service.IStudentService;
import com.mongodb.client.DistinctIterable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.allianzGlobal.assignment.model.Student;
import com.allianzGlobal.assignment.repository.StudentRepository;
import org.bson.types.ObjectId;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

    private static final Logger logger = LogManager.getLogger(AssignmentApplication.class);
    @Autowired
    private IStudentService studentService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    StudentRepository studentRepository;


    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        logger.info("Entered addStudent Method ");
        try {
            String savedMessage =  studentService.addStudent(student);
            return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") int studentId) {
        logger.info("Entered getStudentById Method ");
        Optional<Student> studentData =  studentService.getStudentById(studentId);
        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudentDetailsById(@PathVariable("studentId") int studentId) {
        logger.info("Entered deleteStudentDetailsById Method ");
        try {
            String messageOnDelete = studentService.deleteStudentById(studentId);
            return new ResponseEntity<>(messageOnDelete, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        logger.info("Entered updateStudent Method ");
        try{
            Student updatedStudentData =  studentService.updateStudentById(studentId, student);
            return new ResponseEntity<>(updatedStudentData , HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

//    Custom Api to get filtered result

    @GetMapping("/students/marks")
    List<Student> getAllStudentByLowMarks (){
     List student =  studentService.getAllStudentByLowMarks();
     return new ResponseEntity<>(student , HttpStatus.OK).getBody();
     }

/*

    @GetMapping("/students/distinctName")
    DistinctIterable<Student>  findStudentDistinctName() {
        return mongoTemplate.getCollection("studentDetails").distinct("studentName", Student.class);
    }

    @GetMapping("/students/gender")
    List<Student>  findStudentListByGender() {
        Query query = new Query();
        query.addCriteria(Criteria.where("gender").is("female").andOperator().where("gender").is("male"));
        return  mongoTemplate.find(query, Student.class);
    }


    @GetMapping("/students/genderTest")
    List<Student>  findStudentListByGenderTest() {
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("gender").is("male"));
        var  maleList = mongoTemplate.find(query1, Student.class);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("gender").is("female"));
        var femaleList = mongoTemplate.find(query2, Student.class);

        return mergeGenderlist(maleList, femaleList);
    }
    List<Student> mergeGenderlist(List<Student> queryResult1 , List<Student> queryResult2) {
        ArrayList result = new ArrayList();
        result.add(queryResult1);
        result.add(queryResult2);
        return result;
    }*/

   /* @Query(value = "{ companyType: ?0, companyId: ?1, eventType: ?2, delivered: ?3, " +
                   "$or:[ {requestPayload.sponsorGovernmentId: ?4}, {requestPayload.invoiceNumber: ?5} ]}")*/
    /* public List<Student>  getGenderCollection() {
         MergeOperation mergeOperation = Aggregation.merge()
                 .intoCollection("studentDetails")
                 .on("gender")
                 .whenMatched(MergeOperation.WhenDocumentsMatch.mergeDocuments())
                 .whenNotMatched(MergeOperation.WhenDocumentsDontMatch.discardDocument())
                 .build();
         return mergeOperation.getFields("gende");
     } */

}
