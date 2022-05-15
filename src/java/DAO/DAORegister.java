/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Customers;
import entity.Employee;
import entity.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ngock
 */
public class DAORegister {
//get all acount tu customer and product
    public List<Account> gellAllAcount() {
        List<Account> listAccount = new ArrayList<>();
        List<Customers> listCustomers = new DAOCustomer().listAllCustommer();
        List<Employee> listEmployee = new DAOEmployee().listAllEmployees();
        for (Employee emp : listEmployee) {
            Account listemp = Account.builder()
                    .username(emp.getUsername())
                    .password(emp.getPassword())
                    .roll(emp.getRoll())                   
                    .build();
            listAccount.add(listemp);
        }
        for (Customers cus : listCustomers) {
            Account listcus = Account.builder()
                    .username(cus.getUsername())
                    .password(cus.getPassword())
                    .roll(cus.getRoll())
                    .build();
            listAccount.add(listcus);
        }
        return listAccount;
    }

//random customer Id
    private String ranDomCustomerId(int length) {
        String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLowerCase = asciiUpperCase.toLowerCase();
        String asciiChars = asciiUpperCase + asciiLowerCase;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Random rand = new Random();
        while (i < length) {
            sb.append(asciiChars.charAt(rand.nextInt(asciiChars.length())));
            i++;
        }
        return sb.toString();
    }

    //ktra xem co trung ID khong
    public boolean checkDuplicateCustomerId(String customerId) {
        DAOCustomer dao = new DAOCustomer();
        List<Customers> listCustomers = dao.listAllCustommer();
        for (Customers cus : listCustomers) {
            if (cus.getCustomerid().equals(customerId)) {
                return true;
            }
        }
        return false;
    }
//kiem tra xem username co khong
    public boolean checkDuplicateUsername(String username) {
        DAOCustomer dao = new DAOCustomer();
        List<Customers> listCustomers = dao.listAllCustommer();
        for (Customers cus : listCustomers) {
            if (cus.getUsername()!= null) {
                if (cus.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSignUp(String user,String pass) {
        while (true) {
            Customers cus = Customers.builder()
                    .username(user)
                    .password(pass)
                    .build();
            String random = ranDomCustomerId(5).toUpperCase();
            cus.setCustomerid(random);
            if(user !=null && pass != null) {
            if (!checkDuplicateCustomerId(random)) {
                if (!checkDuplicateUsername(user)) {
                    DAOCustomer dao = new DAOCustomer();
                    dao.addCustomer(cus);                   
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        }
        
    }

    public static void main(String[] args) {
        DAORegister dao = new DAORegister();
        List list = dao.gellAllAcount();
        for (Object object : list) {
            System.out.println(object);
        }
    }

}
