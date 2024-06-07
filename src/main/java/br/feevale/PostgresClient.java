package br.feevale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresClient {

    // sim eu cometi um lindo erro de digitação na hora de criar o banco
    private static final String URL = "jdbc:postgresql://localhost/porg2_trabalho";
    private static final String USER = "uprog2";
    private static final String PASSWORD = "senhasecreta";

    private Connection conn;

    public PostgresClient() {
        try {
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão obtida.");
        } catch (SQLException e) {
            System.out.println("Erro obtendo conexão com o banco. [.:]" + e);
        }
    }

    public Connection getConnection() {
        return this.conn;
    }
}