/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DBContext.DBContext;
import entity.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngock
 */
public class DAOCustomer {

    public ResultSet getData(String sql) {
        Connection conn;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List<Customers> listAllCustommer() {
        String sql = "select * from Customers";
        List<Customers> List = new ArrayList<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String cusId = rs.getString("CustomerID");
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String postalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                String username = rs.getString(12);
                String password = rs.getString(13);
                int roll = rs.getInt(14);

                Customers cus = Customers.builder()
                        .customerid(cusId)
                        .companyname(companyName)
                        .contactname(contactName)
                        .address(address)
                        .city(city)
                        .region(region)
                        .postalcode(postalCode)
                        .country(country)
                        .phone(phone)
                        .fax(fax)
                        .username(username)
                        .password(password)
                        .roll(roll)
                        .build();

                List.add(cus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;
    }

    public void addCustomer(Customers cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]"
                + "           ([CustomerID]"
                + "           ,[CompanyName]"
                + "           ,[ContactName]"
                + "           ,[ContactTitle]"
                + "           ,[Address]"
                + "           ,[City]"
                + "           ,[Region]"
                + "           ,[PostalCode]"
                + "           ,[Country]"
                + "           ,[Phone]"
                + "           ,[Fax]"
                + "           ,[username]"
                + "           ,[password]"
                + "           ,[roll])"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, cus.getCustomerid());
            ps.setString(2, cus.getCompanyname());
            ps.setString(3, cus.getContactname());
            ps.setString(4, cus.getContacttitle());
            ps.setString(5, cus.getAddress());
            ps.setString(6, cus.getCity());
            ps.setString(7, cus.getRegion());
            ps.setString(8, cus.getPostalcode());
            ps.setString(9, cus.getCountry());
            ps.setString(10, cus.getPhone());
            ps.setString(11, cus.getFax());
            ps.setString(12, cus.getUsername());
            ps.setString(13, cus.getPassword());
            ps.setInt(14, 0);

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Customers getCusIDByUserName(String userName) {
        String sql = "select CustomerID from Customers where UserName =?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, userName);
            //Thực thi và trả về kết quả
            ResultSet rs = pre.executeQuery();
            //Run lặp lấy dữ liệu
            while (rs.next()) {
                Customers cus = Customers.builder()
                        .customerid(rs.getString(1))
                        .build();
                return cus;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int updateCustomers(Customers cus) {
        int n = 0;
        String sql = "update Customers set CompanyName=?,ContactName=?,"
                + "ContactTitle=?,Address=?,City=?,Region=?,PostalCode=?,Country=?, "
                + "Phone=?, Fax=?,userName=?,password=? where CustomerID=?";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCompanyname());
            pre.setString(2, cus.getContactname());
            pre.setString(3, cus.getContacttitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPostalcode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getUsername());
            pre.setString(12, cus.getPassword());
            pre.setString(13, cus.getCustomerid());
            
            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public  List getCusId() {
        List list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select CustomerID from Customers where CustomerID not in (select CustomerID from Orders)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int deleteCustomer(String id){
       int n = 0;
        String sql = "delete from Customers where CustomerID = ?";
         Connection conn;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public  List getOderDetailsId() {
        List list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select ProductID from Products where ProductID in (select ProductID from [Order Details])";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int deleteProductIdInOrderDetail(String id){
       int n = 0;
        String sql = "delete from [Order Details] where ProductID =?";
         Connection conn;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
     public int deleteProduct(String id){
       int n = 0;
        String sql = "delete from Products where ProductID =?";
         Connection conn;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
}
