package com.aitu.entities;

import com.aitu.exceptions.AgeOutOfBoundsException;
import com.aitu.interfaces.Control;

public class Administrator extends Person implements Control {

    public Administrator(String firstName, String lastName, int age) throws AgeOutOfBoundsException {
        super(firstName, lastName, age);
    }

    @Override
    public void addStudent(Student student, Dormitory block, int floor, int room) {
        block.addStudent(student, block, floor, room);
        System.out.println(student.getFirstName() + " was added");
    }

    @Override
    public void removeStudent(Student student, Dormitory block) {
        block.removeStudent(student, block);
        System.out.println(student.getFirstName() + " was removed");
    }

    @Override
    public void sayHello() {

    }
}