package com.aitu.entities;

import com.aitu.exceptions.AgeOutOfBoundsException;
import com.aitu.exceptions.UserExistsException;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person {

    private int floor;
    private int room;
    private int id;
    private Scanner sc = new Scanner(System.in);

    public Student(int id, String firstName, String lastName,
                   int age, int floor, int room) throws UserExistsException, AgeOutOfBoundsException {
        super(firstName, lastName, age);
        setFloorRoom(floor, room);
        setId(id);
    }

    public Student(String firstName, String lastName,
                   int age, int floor, int room) throws AgeOutOfBoundsException {
        super(firstName, lastName, age);
        setFloorRoom(floor, room);
    }

    public int getId() {
        return id;
    }

    public int getRoom() {
        return room;
    }

    public int getFloor() {
        return floor;
    }

    public void setId(int id)
            throws UserExistsException {
        this.id = id;
    }

    public void setFloorRoom(int floor, int room) {
        if (isCorrectRoom(floor, room)) {
            this.floor = floor;
            this.room = room;
        }
        else {
            System.out.println("**!!!!!!!**");
            System.out.println("another room/floor");
            int fl = sc.nextInt();
            int rm = sc.nextInt();
            setFloorRoom(fl, rm);
        }
    }

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

    @Override
    public void sayHello() {
        System.out.println("Hello! I am a student");
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() +
                " ID: " + getId() + ", floor " +
                getFloor() + ", room " + getRoom();
    }
}
