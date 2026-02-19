package com.weg.biblioteca.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3356/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PSWD = "mysqlPW";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PSWD);
    }
}
