/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ngock
 */
public class DAOLogin {
    public int checkLogin(String user,String pass) {
        
       DAORegister dao = new DAORegister();
       List<Account> list= dao.gellAllAcount();
        for (Account a : list) {
            if(a.getUsername() !=null && a.getPassword() !=null) {
            if(a.getUsername().equals(user) && a.getPassword().equals(pass) &&a.getRoll()==0) return 0;
            if(a.getUsername().equals(user) && a.getPassword().equals(pass) &&a.getRoll()==1) return 1;                       
            }
    }
        return 2;
}
}
