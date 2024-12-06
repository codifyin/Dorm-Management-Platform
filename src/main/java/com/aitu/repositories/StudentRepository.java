package com.aitu.repositories;

import com.aitu.data.interfaces.IDB;
import com.aitu.entities.Student;
import com.aitu.exceptions.AgeOutOfBoundsException;
import com.aitu.exceptions.UserExistsException;

import java.sql.*;
import java.util.*;

public class StudentRepository implements IStudentRepository {


    private final IDB db;

    public StudentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addStudent(Student student, int id_bl) {
        Connection connection = null;
        try {
            connection = db.connect();
            String sql = "INSERT INTO students(age, first_name, last_name, floor, room, id_bl) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, student.getAge());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, student.getFloor());
            statement.setInt(5, student.getRoom());
            statement.setInt(6, id_bl);

            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Student removeStudent(int id) {
        Connection connection = null;
        Statement statement;
        try {
            connection = db.connect();
            String sql = "SELECT id_stud,first_name,last_name,age,floor,room FROM students WHERE id_stud=?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student student = new Student(rs.getInt("id_stud"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getInt("floor"),
                        rs.getInt("room"));

                try {
                    String query = String.format("DELETE FROM students WHERE id_stud = %d", id);
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                } catch (Exception e) {
                    System.out.println(e);
                }
                return student;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UserExistsException e) {
            System.out.println(e);;
        } catch (AgeOutOfBoundsException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Student getStudent(int id, int id_bl) {
        Connection connection = null;
        try {
            connection = db.connect();
            String sql = "SELECT id_stud,first_name,last_name,age,floor,room FROM students WHERE id_stud = ? AND id_bl = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, id);
            st.setInt(2, id_bl);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student student = new Student(rs.getInt("id_stud"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getInt("floor"),
                        rs.getInt("room"));

                return student;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UserExistsException e) {
            System.out.println(e);;
        } catch (AgeOutOfBoundsException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<Student> getAllStudents(int id_bl) {
        Connection connection = null;
        try {
            connection = db.connect();
            String sql = String.format("SELECT id_stud,first_name,last_name, age, floor, room FROM students WHERE id_bl = %d", id_bl);
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student(rs.getInt("id_stud"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getInt("floor"),
                        rs.getInt("room"));

                students.add(student);
            }

            return students;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UserExistsException e) {
            throw new RuntimeException(e);
        } catch (AgeOutOfBoundsException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
