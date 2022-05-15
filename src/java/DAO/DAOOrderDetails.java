/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBContext.DBContext;
import entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngock
 */
public class DAOOrderDetails {

    public int addOrderDetail(int OrderID, List<Cart> listProductCarts) {
        int n = 0;
        String sql = "INSERT INTO [Order Details](OrderID,ProductID,"
                + "UnitPrice,Quantity)"
                + "VALUES(?,?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            for (Cart pro : listProductCarts) {
                pre.setInt(1, OrderID);
                pre.setInt(2, pro.getProductid());
                pre.setDouble(3, pro.getPrice());
                pre.setInt(4, pro.getQuantity());
                n = pre.executeUpdate();
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int updateQuantity(String quantity, String orderID, String ProductID) {
        int n = 0;
        String sql = "update [Order Details] set Quantity = ?\n"
                + "where OrderID = ? and ProductID =?";
        Connection conn;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, quantity);
            pre.setString(2, orderID);
            pre.setString(3, ProductID);
            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteOrderDetails(String OrderID,String ProductID) {
        int n = 0;
        String sql = "delete from [Order Details] where OrderID = ? and ProductID =?";
        Connection conn;
        try {
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, OrderID);
            ps.setString(2, ProductID);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
}
