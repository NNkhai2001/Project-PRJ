/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOOrderDetails;
import DAO.DAOOrderList;
import DAO.DAOProduct;
import entity.Cart;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ngock
 */
@WebServlet(name = "BackToCardController", urlPatterns = {"/BackToCard"})
public class BackToCardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            if (service == null) {
                service = "home";
            }
            if (service.equals("home")) {
                DAOOrderList dao = new DAOOrderList();
                int pid = Integer.parseInt(request.getParameter("odId"));
                ResultSet rs = dao.getData("select cus.ContactName,o.ShipAddress,o.OrderDate,(od.Quantity * od.UnitPrice)'total',o.[status] "
                        + ",pro.ProductID,pro.ProductName,od.Quantity,od.UnitPrice,o.OrderID  from [Order Details] od\n"
                        + " join Orders o on o.OrderID = od.OrderID\n"
                        + " join Products pro on od.ProductID = pro.ProductID    \n"
                        + " join Customers cus on o.CustomerID = cus.CustomerID                                                                                       \n"
                        + " where o.OrderID=" + pid);
                String mess = "a";
                request.setAttribute("mess", mess);
                request.setAttribute("detailsOrderList", rs);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            }
            if (service.equals("updatequantity")) {
                DAOOrderDetails dao = new DAOOrderDetails();
                String odId = request.getParameter("odId");
                String pid = request.getParameter("pid");
                String quantity = request.getParameter("quantity");
                dao.updateQuantity(quantity, odId, pid);
                request.getRequestDispatcher("BackToCard?do=home&odId" + odId).forward(request, response);
            }
            if (service.equals("delete")) {
                DAOOrderDetails dao = new DAOOrderDetails();
                String odId = request.getParameter("odId");
                String pid = request.getParameter("pid");
                dao.deleteOrderDetails(odId, pid);
                request.getRequestDispatcher("BackToCard?do=home&odId" + odId).forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
