/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;

/**
 *
 * @author Asano
 */
import com.mysql.jdbc.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConn {

    private Connection conn;
    private Statement stm;

    public databaseConn() {
        if (conn == null) {
            String url = "jdbc:mysql://localhost/";
            String dbName = "eventme";
            String driver = "com.mysql.jdbc.Driver";
            String username = "root";
            String password = "";
            try {
                Class.forName(driver);
                this.conn = (Connection) DriverManager.getConnection(url + dbName, username, password);
                System.out.println("CONNECTION SUCCESSFUL!");
            } catch (ClassNotFoundException | SQLException sqle) {
                System.out.println("CONNECTION FAILED");
            }
        }
        try {
            stm = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("Create Statement");
            System.out.println(ex);
        }

    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
