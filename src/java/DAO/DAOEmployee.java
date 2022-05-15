/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DBContext.DBContext;
import entity.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngock
 */
public class DAOEmployee {
    public ResultSet getData(String sql) {
    Connection conn;   
    ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
             Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE
                   ,ResultSet.CONCUR_UPDATABLE);
           rs = state.executeQuery(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public List<Employee> listAllEmployees(){
         String sql = "select * from Employees";
        List<Employee> List = new Vector<Employee>();
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int empID = rs.getInt("EmployeeID");
                String lName = rs.getString(2);
                String fName = rs.getString(3);
                String title = rs.getString(4);
                String TOC = rs.getString(5);
                String birthDate = rs.getString(6);
                String hireDate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String postalCode = rs.getString(11);
                String country = rs.getString(12);
                String homePhone = rs.getString(13);
                String extension = rs.getString(14);
                String photo = rs.getString(15);
                String notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                String username = rs.getString(19);
                String password = rs.getString(20);
                int roll = rs.getInt(21);
                
                Employee emp = Employee.builder()
                        .employeeid(empID)
                        .lastname(lName)
                        .firstname(fName)
                        .title(title)
                        .titleofcourtesy(TOC)
                        .birthdate(birthDate)
                        .hiredate(hireDate)
                        .address(address)
                        .city(city)
                        .region(region)
                        .postalcode(postalCode)
                        .country(country)
                        .homephone(homePhone)
                        .extension(extension)
                        .photo(photo)
                        .notes(notes)
                        .reportsto(reportsTo)
                        .photopath(photoPath)
                        .username(username)
                        .password(password)
                        .roll(roll)
                        .build();
                
                List.add(emp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;
    }
}
