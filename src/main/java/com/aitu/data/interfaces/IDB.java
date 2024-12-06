package com.aitu.data.interfaces;

import java.sql.*;

public interface IDB {

    Connection connect() throws SQLException, ClassNotFoundException;
}
