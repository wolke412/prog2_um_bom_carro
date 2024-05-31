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

    public ResultSet Query(String query) {
       try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            return rs;
      } 
        catch ( SQLException e ) {
            return null;
       } 
    }

    public void select() {
        String SQL = "SELECT * FROM testtab";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("val_a") + " " +
                                rs.getString("val_b"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(int id) {
        String SQL = "DELETE FROM testtab WHERE id = ?";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}