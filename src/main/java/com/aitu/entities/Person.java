package com.aitu.entities;

import com.aitu.exceptions.AgeOutOfBoundsException;

import java.util.Scanner;

public abstract class Person {

    private String firstName;
    private String lastName;
    private int age;
    private Scanner sc = new Scanner(System.in);

    public Person(String firstName, String lastName, int age) throws AgeOutOfBoundsException {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            setAge(age);
        } catch (AgeOutOfBoundsException e) {
            System.out.println(e.getMessage());

            while(true) {
                System.out.print("Age is invalid! Another age: ");
                int age2 = sc.nextInt();
                if (isCorrectAge(age2)) {
                    setAge(age2);
                    break;
                }
            }
        }
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) throws AgeOutOfBoundsException {
        if (age < 0 || age > 100)
            throw new AgeOutOfBoundsException("age range");
        this.age = age;
    }

    public boolean isCorrectAge (int age) {
        if (age > 0 && age < 100) return true;
        else return false;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public abstract void sayHello();

}
