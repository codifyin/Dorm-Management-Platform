package com.aitu;

import com.aitu.data.PostgresDB;
import com.aitu.data.interfaces.IDB;
import com.aitu.entities.Student;

import java.sql.*;
import java.util.Objects;

public class Authenticator implements IAuthenticator {

    PostgresDB db = new PostgresDB();

    public boolean checkCredentials(String login, String password) {
        Connection connection = null;
        try {
            connection = db.connect();
            connection = db.connect();
            String sql = "SELECT login, password FROM admins";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("login").equals(login) && rs.getString("password").equals(password))
                    return true;
            }

            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}