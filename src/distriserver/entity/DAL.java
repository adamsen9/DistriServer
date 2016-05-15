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
    
    static Connection conn;
    static Statement stmt;
    Buffer buffer;
    
    public DAL() {
        buffer = new Buffer();
    }
    
    public boolean addPositiveVote(String userID) {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "UPDATE users SET positivescore = positivescore + 1 WHERE user_id = +\"" + userID + "\"";
            sql = String.format(Locale.US, sql);
            stmt = conn.createStatement();
            
            return stmt.execute(sql);
        }
         catch(SQLException e) {
             
             return false;
         }
        
    }

    public boolean addNegativeVote(String userID) {
        try {
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "UPDATE users SET positivescore = negativescore + 1 WHERE user_id = +\"" + userID + "\"";
            sql = String.format(Locale.US, sql);
            stmt = conn.createStatement();
            
            
            
            return stmt.execute(sql);
        }
         catch(SQLException e) {
             
             return false;
         }
    }

    public String getUserStats(String userID) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM users WHERE user_id = '" + userID + "'";
            sql = String.format(Locale.US, sql);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
                return rs.getString("positivescore") + " " + rs.getString("negativescore");
            
        } catch (SQLException e) {
            System.out.println("SQL Fejl: " + e.getMessage());
            e.printStackTrace();
        }
            return "Error";
    }
    
    public Buffer getBuffer() {
        return buffer;
    }

}