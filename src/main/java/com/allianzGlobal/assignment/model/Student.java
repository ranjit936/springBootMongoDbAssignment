package com.allianzGlobal.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "studentDetails")
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private int rollNo;
    private int marks;
    private String subject;
    private String gender;
    private String country;

}
