/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DBContext.DBContext;
import entity.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngock
 */
public class DAOOrders {

    public int addOrder(Orders ord) {
        String sql = "insert into Orders(CustomerID ,ShipAddress,status,total) \n"
                + "values (?,?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, ord.getCustomerid());
            pre.setString(2, ord.getShipaddress());
            pre.setInt(3, ord.getStatus());
            pre.setDouble(4, ord.getTotal());
            pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return 0;

    }

    
}
