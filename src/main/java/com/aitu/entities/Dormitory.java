// The AITU Dormitory building plan.

package com.aitu.entities;

import com.aitu.interfaces.Control;

import java.util.ArrayList;
import java.util.Scanner;

public class Dormitory implements Control {

    private final int numberOfFloors = 7;
    private final int roomsPerFloor = 18;
    private final int totalNumberOfRooms = 126;
    private ArrayList<Student> studentRooms = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getTotalNumberOfPlaces() {
        return totalNumberOfRooms;
    }

    //info about all students
    public void info() {
        System.out.println(studentRooms);
    }

    //info about all students on the specific floor
    public void info(int floor) {
        int roomLower = (floor - 1) * roomsPerFloor + 1;
        int roomUpper = floor * roomsPerFloor;

        for (Student n : studentRooms) {
            if(n.getRoom() >= roomLower && n.getRoom() <= roomUpper) {
                System.out.println(n);
            }
        }
    }

    //checks if the input is correct 1 floor 1-18 rooms, 2 floor 19-36 rooms ...
    private boolean isCorrectRoom(int floor, int room) {
        if((floor < 1 || floor > 7) || (room < 1 || room > 126)) return false;
        int roomLower = (floor - 1) * 18 + 1;
        int roomUpper = floor * 18;
        if(room >= roomLower && room <= roomUpper) {
            return true;
        } else {
            return false;
        }
    }

    private void setStudent(Student student, int floor, int room) {
        if(!isCorrectRoom(floor, room)) {
            throw new IllegalArgumentException(student.getFirstName() +
                    ' ' + student.getLastName() + " floor/room mistake");
        } else {
            student.setFloorRoom(floor, room);
        }
    }

    @Override
    public void addStudent(Student student, Dormitory block, int floor, int room) {
        try {
            setStudent(student, floor, room);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            while(true) {
                System.out.print("another floor room: ");
                int newFloor = sc.nextInt();
                int newRoom = sc.nextInt();
                if(isCorrectRoom(newFloor, newRoom)) {
                    student.setFloorRoom(newFloor, newRoom);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("OK");
        }

        studentRooms.add(student);
    }

    @Override
    public void removeStudent(Student student, Dormitory block) {
        studentRooms.remove(student);
    }
}
