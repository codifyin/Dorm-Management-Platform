package com.aitu.interfaces;

import com.aitu.entities.Dormitory;
import com.aitu.entities.Student;

public interface Control {

    void addStudent(Student student, Dormitory block, int floor, int room);

    void removeStudent(Student student, Dormitory block);
}
