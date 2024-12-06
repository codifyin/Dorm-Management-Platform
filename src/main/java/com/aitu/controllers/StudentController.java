package com.aitu.controllers;

import com.aitu.entities.Student;
import com.aitu.exceptions.AgeOutOfBoundsException;
import com.aitu.exceptions.UserExistsException;
import com.aitu.repositories.IStudentRepository;

import java.util.ArrayList;

public class StudentController {

    private final IStudentRepository repository;

    public StudentController(IStudentRepository repository) {
        this.repository = repository;
    }

    public String addStudent (String name, String surname, int age, int floor, int room, int id_bl) throws UserExistsException, AgeOutOfBoundsException {
        Student student = new Student(name, surname, age, floor, room);

        boolean added = repository.addStudent(student, id_bl);

        if (added) System.out.println(String.format("floor: %d, room: %d", floor, room));

        return (added ? "Student was added!" : "Student adding was failed!");
    }

    public String removeStudent (int id) {
        Student student = repository.removeStudent(id);

        return student == null ? "Student doesn't exist" : "Student was removed";
    }

    public String getStudent (int id, int id_bl) {
        Student student = repository.getStudent(id, id_bl);

        return student == null ? "Student was not found" : student.toString();
    }

    public String getAllStudents (int id_bl) {
        ArrayList<Student> students = repository.getAllStudents(id_bl);
        return students.toString();
    }
}
