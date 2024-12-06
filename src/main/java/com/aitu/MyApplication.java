package com.aitu;

import com.aitu.controllers.StudentController;
import com.aitu.entities.Dormitory;
import com.aitu.exceptions.AgeOutOfBoundsException;
import com.aitu.exceptions.UserExistsException;

import java.util.*;

public class MyApplication {

    private final Scanner scanner;
    private final StudentController controller;
    private int id_bl;


    public MyApplication(StudentController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {

        Authenticator authenticator = new Authenticator();

        while (true) {
            System.out.println("Please enter your login:");
            String login = scanner.nextLine();
            System.out.println("Please enter you password");
            String password = scanner.nextLine();
            if (authenticator.checkCredentials(login, password)) {
                System.out.println("good!");
                id_bl = login.charAt(5) - 48;
                break;
            } else {
                System.out.println("Something goes wrong. Try again");
            }
        }

        while (true) {
            System.out.print("""
                                    
                    Welcome to the Dormitory Management Application!
                    Please select an option:
                    1. Retrieve all students
                    2. Retrieve student by ID
                    3. Add student
                    4. Remove student
                    0. Exit
                                    
                    """);

            try {
                System.out.print("Enter option (1-4): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getAllStudentsMenu();
                } else if (option == 2) {
                    getStudentByIdMenu();
                } else if (option == 3) {
                    addStudentMenu();
                } else if (option == 4) {
                    removeStudentMenu();
                } else {
                    System.out.println("terminating...");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("**************************************************");
        }
    }


    public void getAllStudentsMenu() {
        String response = controller.getAllStudents(id_bl);
        System.out.println(response);
    }

    public void getStudentByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        String response = controller.getStudent(id, id_bl);
        System.out.println(response);
    }

    public void addStudentMenu() throws UserExistsException, AgeOutOfBoundsException {
        System.out.println("Please enter the name");
        String name = scanner.next();
        System.out.println("Please enter the surname");
        String surname = scanner.next();
        System.out.println("Please enter the age");
        int age = scanner.nextInt();
        System.out.println("Please enter the floor");
        int floor = scanner.nextInt();
        System.out.println("Please enter the room");
        int room = scanner.nextInt();

        String response = controller.addStudent(name, surname, age, floor, room, id_bl);
        System.out.println(response);
    }

    public void removeStudentMenu() {
        System.out.println("Please enter id: ");
        int id = scanner.nextInt();

        String response = controller.removeStudent(id);
        System.out.println(response);
    }
}
