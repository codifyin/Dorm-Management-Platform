/*
DormitoryManagement
Assignment 3 IT-2208

Contributors: Iliya Lim, Islambek Nurzhanov, Sanzhar Zhagiparov, Amirzhan Issa

The program helps to manage the day-to-day affairs of the AITU Dormitory
 */

package com.aitu;

import com.aitu.controllers.StudentController;
import com.aitu.data.PostgresDB;
import com.aitu.data.interfaces.IDB;
import com.aitu.repositories.IStudentRepository;
import com.aitu.repositories.StudentRepository;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB();
        IStudentRepository repo = new StudentRepository(db);
        StudentController controller = new StudentController(repo);
        MyApplication app = new MyApplication(controller);
        app.start();

    }
}