/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DBContext.DBContext;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngock
 */
public class DAOCategory {

    public List<Category> getAllCategory() {
        List<Category> List = new ArrayList<>();
        try {
            String sql = "select * from Categories";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Category category = Category.builder()
                        .categoryid(rs.getInt(1))
                        .categoryname(rs.getString(2))
                        .description(rs.getString(3))
                        .picture(rs.getString(4)).build();
                List.add(category);
            }
        } catch (Exception ex) {
            
           ex.printStackTrace();
        }
        return List;
    }

    public int addCategories(Category cate) {
        int n = 0;
        String sql = "insert into Categories(CategoryName, Description)" +
        " values(?,?)";
        try {
            Connection conn = new DBContext().getConnection();
             PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryname());
            pre.setString(2, cate.getDescription());
            //run
            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    
    
}
