/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBContext.DBContext;
import entity.OrderList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngock
 */
public class DAOOrderList {

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

    public List<OrderList> listAllOrderList() {
        List<OrderList> list = new ArrayList<>();
        String sql = "select o.OrderID,cus.ContactName,o.OrderDate,o.total,o.status from Orders o join Customers cus on o.CustomerID = cus.CustomerID";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String contactName = rs.getString(2);
                String OrderDate = rs.getString(3);
                double total = rs.getDouble(4);
                int status = rs.getInt(5);
                OrderList ord = OrderList.builder()
                        .orderid(ordId)
                        .contactname(contactName)
                        .orderdate(OrderDate)
                        .total(total)
                        .status(status)
                        .build();
                list.add(ord);
            }

        } catch (Exception ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public int updateStatus(int status, int orId) {
        int n = 0;
        String sql = "update Orders set status=? where OrderID=?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, orId);
            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOOrderList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<OrderList> listOrderListbyusername(String username) {
        List<OrderList> list = new ArrayList<>();
        String sql = "select o.OrderID,cus.ContactName,o.OrderDate,o.total,o.status from Orders o join Customers cus on o.CustomerID = cus.CustomerID"
                + " where cus.username =?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String contactName = rs.getString(2);
                String OrderDate = rs.getString(3);
                double total = rs.getDouble(4);
                int status = rs.getInt(5);
                OrderList ord = OrderList.builder()
                        .orderid(ordId)
                        .contactname(contactName)
                        .orderdate(OrderDate)
                        .total(total)
                        .status(status)
                        .build();
                list.add(ord);
            }

        } catch (Exception ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<OrderList> listAllOrderListbystatus(String Status) {
        List<OrderList> list = new ArrayList<>();
        String sql = "select o.OrderID,cus.ContactName,o.OrderDate,o.total,o.[status] from Orders o join Customers cus on o.CustomerID = cus.CustomerID \n"
                + "where o.[status] =?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ordId = rs.getInt(1);
                String contactName = rs.getString(2);
                String OrderDate = rs.getString(3);
                double total = rs.getDouble(4);
                int status = rs.getInt(5);
                OrderList ord = OrderList.builder()
                        .orderid(ordId)
                        .contactname(contactName)
                        .orderdate(OrderDate)
                        .total(total)
                        .status(status)
                        .build();
                list.add(ord);
            }

        } catch (Exception ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
         
    }
     public  List getOderIDinOrderDetails() {
        List list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select OrderID from Orders where OrderID  in (select OrderID from [Order Details])";
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
      public int deleteOderIDInOrderDetail(String id){
       int n = 0;
        String sql = "delete from [Order Details] where OrderID =?";
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
      public int deleteStatus(String id){
       int n = 0;
        String sql = "delete from Orders where OrderID =?";
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
