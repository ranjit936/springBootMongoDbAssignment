package com.allianzGlobal.assignment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "studentDetails" )
public class Student {
    @Id
    private int rollNo;
    private String studentName;


    private int marks;
    private String gender;

    public Student() {

    }
    public Student(int rollNo, String studentName, int marks, String gender) {
        this.rollNo = rollNo;
        this.studentName = studentName;
        this.marks = marks;
        this.gender = gender;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student Details [rollNo=" + rollNo + ", studentName=" + studentName + ", gender=" + gender + ", marks=" + marks + "]";
    }
}
