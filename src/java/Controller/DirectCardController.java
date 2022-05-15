/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOOrderList;
import entity.Account;
import entity.OrderList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "DirectCardController", urlPatterns = {"/DirectCardController"})
public class DirectCardController extends HttpServlet {

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
            String service = request.getParameter("do");
            if (service == null) {
                service = "DirectCard";
            }
            if (service.equals("DirectCard")) {
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("accountcus");
                if (acc == null) {
                    response.sendRedirect("Login.jsp");
                } else {
                    String username = acc.getUsername();
                    DAOOrderList dao = new DAOOrderList();
                    List<OrderList> list = dao.listOrderListbyusername(username);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("DirectCard.jsp").forward(request, response);
                }
            }
            if (service.equals("details")) {
                DAOOrderList dao = new DAOOrderList();
                int pid = Integer.parseInt(request.getParameter("odId"));
                ResultSet rs = dao.getData("select cus.ContactName,o.ShipAddress,o.OrderDate,(od.Quantity * od.UnitPrice)'total',o.[status] "
                        + ",pro.ProductID,pro.ProductName,od.Quantity,od.UnitPrice,o.OrderID  from [Order Details] od\n"
                        + " join Orders o on o.OrderID = od.OrderID\n"
                        + " join Products pro on od.ProductID = pro.ProductID    \n"
                        + " join Customers cus on o.CustomerID = cus.CustomerID                                                                                       \n"
                        + " where o.OrderID=" + pid);
                request.setAttribute("detailsOrderList", rs);
                request.getRequestDispatcher("DirectCardDetails.jsp").forward(request, response);
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
