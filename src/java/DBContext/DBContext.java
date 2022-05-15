/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
//      public String getImagePath() throws Exception {
//        return "image/";
//    } 
    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "NNKhai";
    private final String dbName = "SE1613";
    private final String portNumber = "1433";
    private final String instance = "";//LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "123456";

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Connection conn = new DBContext().getConnection();
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;

    }

    public static void main(String[] args) {
        try {
            DBContext conn = new DBContext();
            if (conn.getConnection() != null) {
                System.out.println("success");
            } else {
                System.out.println("fail!");
            }
        } catch (Exception e) {
        }
    }

}
