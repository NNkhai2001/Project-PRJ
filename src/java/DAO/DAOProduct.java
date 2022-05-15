/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBContext.DBContext;
import entity.Category;
import entity.Products;
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
public class DAOProduct {

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

    //Get List Phân Trang
    public List<Products> getAllProductsWithPaging(int page, int PAGE_SIZE) {
        List<Products> listPro = new ArrayList<>();
        String sql = "SELECT * FROM\n"
                + "( SELECT     *,\n"
                + "ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq\n"
                + "FROM         Products )t\n"
                + "WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Products pro = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    public List<Products> getProductsWithPagingbtId(int id, int page, int PAGE_SIZE) {
        List<Products> listPro = new ArrayList<>();
        String sql = "SELECT * FROM\n"
                + "               ( SELECT     *, ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq FROM Products \n"
                + "                where CategoryID = ?\n"
                + "                 )t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Products pro = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }
    public List<Products> getProductsWithPagingbtname(String name, int page, int PAGE_SIZE) {
        List<Products> listPro = new ArrayList<>();
        String sql = "SELECT * FROM\n"
                + "               ( SELECT     *, ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq FROM Products \n"
                + "                where ProductName like ?\n"
                + "                 )t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        try {
            //Mở kết nốt
            Connection conn = new DBContext().getConnection();
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,"%"+name+"%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Products pro = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                listPro.add(pro);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    public List<Products> getAllProduct() {
        List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                List.add(Product);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return List;
    }

    public List<Products> getProductByCategoryID(int cid) {
        List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products where CategoryID=?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                List.add(Product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return List;
    }

    public int getTotalProduct() {
        try {
            String sql = "select count(ProductID) from Products ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
        public int getTotalProductbyId(int id) {
        try {
            String sql = "select count(ProductID) from Products where CategoryID =? ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
     public int getTotalProductbyName(String name) {
        try {
            String sql = "select count(ProductID) from Products where ProductName like ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }   

    public List<Products> Sreach(String key) {
        List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products where ProductName like ? ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                List.add(Product);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return List;
    }

    public Products getProductById(int pid) {
        List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products where ProductID  =? ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                return Product;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return null;
    }

    public Products getProductByKey(String key) {
        List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products where ProductID  =? ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                return Product;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return null;
    }

    public int updateProducts(Products pro) {
        int n = 0;
        String sql = "update Products set ProductName = ?,SupplierID=?,CategoryID=?,"
                + "QuantityPerUnit=?,UnitPrice=?,UnitsInStock=?,UnitsOnOrder=?,"
                + "ReorderLevel=?,Discontinued=?,Picture=? where ProductID = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductname());
            pre.setInt(2, pro.getSupplierid());
            pre.setInt(3, pro.getCategoryid());
            pre.setString(4, pro.getQuantityperunit());
            pre.setDouble(5, pro.getUnitprice());
            pre.setInt(6, pro.getUnitsinstock());
            pre.setInt(7, pro.getUnitsonorder());
            pre.setInt(8, pro.getReorderlevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setString(10, pro.getPicture());
            pre.setInt(11, pro.getProductid());
            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProducts(Products pro) {
        int n = 0;
        String sql = "insert into Products(ProductName,SupplierID,CategoryID,"
                + "QuantityPerUnit,UnitPrice,UnitsInStock,UnitsOnOrder,"
                + "ReorderLevel,Discontinued,Picture)"
                + " values(?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductname());
            pre.setInt(2, pro.getSupplierid());
            pre.setInt(3, pro.getCategoryid());
            pre.setString(4, pro.getQuantityperunit());
            pre.setDouble(5, pro.getUnitprice());
            pre.setInt(6, pro.getUnitsinstock());
            pre.setInt(7, pro.getUnitsonorder());
            pre.setInt(8, pro.getReorderlevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setString(10, pro.getPicture());

            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<Products> getProductbyPrice(String From, String To) {
       List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products where UnitPrice between ? and ? ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, From);
            ps.setString(2, To);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                List.add(Product);
            }
            return List;
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return null;
    }
     public List<Products> getProductbyName(String name) {
       List<Products> List = new ArrayList<>();
        try {
            String sql = "select * from Products where ProductName like ? ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%" +name+ "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Products Product = Products.builder()
                        .productid(rs.getInt(1))
                        .productname(rs.getString(2))
                        .supplierid(rs.getInt(3))
                        .categoryid(rs.getInt(4))
                        .quantityperunit(rs.getString(5))
                        .unitprice(rs.getDouble(6))
                        .unitsinstock(rs.getInt(7))
                        .unitsonorder(rs.getInt(8))
                        .reorderlevel(rs.getInt(9))
                        .discontinued(rs.getInt(10))
                        .picture(rs.getString(11))
                        .build();
                List.add(Product);
            }
            return List;
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return null;
    }
//    public static void main(String[] args) {
//        DAOProduct dao = new DAOProduct();
//        List<Products> list = dao.getProductbyPrice(100, 200);
//        System.out.println(list);
//    }

}
