/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.entity;

import java.sql.*;
import java.util.Locale;

//Hvilken driver anvendes
public class DAL {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //URL
    static final String DB_URL = "jdbc:mysql://www.db4free.net/galgedatabase";

    //Bruger
    static final String USER = "fsabase";
    //Pass
    static final String PASS = "clancbsfryser2";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "select * from users";
            sql = String.format(Locale.US, sql);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("usersname") + " id: " + rs.getString("user_id"));
            }

        } catch (SQLException e) {
            System.out.println("SQL Fejl: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean addPositiveVotes(int votes, String userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addNegativeVotes(int votes, String userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUserStats(String userID) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM users WHERE userid = '" + userID + "'";
            sql = String.format(Locale.US, sql);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
                return rs.getString("positivescore") + " " + rs.getString("negativescore");
            
        } catch (SQLException e) {
            System.out.println("SQL Fejl: " + e.getMessage());
            e.printStackTrace();
        }
            return "Error";
    }

}