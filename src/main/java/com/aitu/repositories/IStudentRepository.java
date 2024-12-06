package com.aitu.repositories;

import com.aitu.entities.Student;

import java.util.ArrayList;

public interface IStudentRepository {

    boolean addStudent(Student student, int id_bl);
    Student removeStudent(int id);
    Student getStudent(int id, int id_bl);
    ArrayList<Student> getAllStudents(int id_bl);
}
