package com.allianzGlobal.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.allianzGlobal.assignment.AssignmentApplication;
import com.mongodb.client.DistinctIterable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private MongoTemplate mongoTemplate;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        logger.info("Entered addStudent Method ");
        try {
            Student save = this.studentRepository.save(student);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/students/{rollNo}")
    public ResponseEntity<Student> getStudentById(@PathVariable("rollNo") int rollNo) {
        logger.info("Entered getStudentById Method ");
        Optional<Student> studentData = this.studentRepository.findById(rollNo);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{rollNo}")
    public ResponseEntity<HttpStatus> deleteStudentDetailsById(@PathVariable("rollNo") int rollNo) {
        logger.info("Entered deleteStudentDetailsById Method ");
        try {
            this.studentRepository.deleteById(rollNo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{rollNo}")
    public ResponseEntity<Student> updateStudent(@PathVariable("rollNo") int rollNo, @RequestBody Student student) {
        logger.info("Entered updateStudent Method ");
        Optional<Student> studentData = this.studentRepository.findById(rollNo);

        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setStudentName(student.getStudentName());
            _student.setGender(student.getGender());
            _student.setRollNo(student.getRollNo());
            _student.setMarks(student.getMarks());
            return new ResponseEntity<>(this.studentRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



//    Api to get filtered

    @GetMapping("/students/marks")
    List<ObjectId> findStudentByMarks (){
        Query query = new Query();
        query.addCriteria(Criteria.where("marks").lt(35));
       return  mongoTemplate.find(query, ObjectId.class );
    }

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
       }

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
