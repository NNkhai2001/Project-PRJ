/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOCustomer;
import DAO.DAOLogin;
import DAO.DAOOrderDetails;
import DAO.DAOOrders;
import entity.Account;
import entity.Cart;
import entity.Customers;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.criteria.Order;
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
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

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
            String customer = request.getParameter("CustomerID");
            String Total = request.getParameter("total");
            String address = request.getParameter("address");
            String status = request.getParameter("status");
            double total = Double.parseDouble(Total);
            int Status = Integer.parseInt(status);
            Orders ord = Orders.builder()
                    .customerid(customer)
                    .shipaddress(address)
                    .status(Status)
                    .total(total)
                    .build();
            int OrderID = new DAOOrders().addOrder(ord);

            //laay Product tren session
            HttpSession session = request.getSession();
            List<Cart> listProductCarts = new ArrayList<>();
            Account acc = (Account) session.getAttribute("accountcus");
            String cusid = acc.getCustomerid();
            request.setAttribute("cusid", cusid);
            Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString();
                if (!key.equals("urlHistory") && !key.equals("ListCategory") && !key.equals("accountcus") && !key.equals("size") && !key.equals("accountemp") && !key.equals("Page")) {
                    Cart pro = (Cart) session.getAttribute(key);
                    listProductCarts.add(pro);
                }
            }
            int n = new DAOOrderDetails().addOrderDetail(OrderID, listProductCarts);
           
//            out.print("<h3> checkout success </h3>"
//                    + "<button> <a href=\"HomeController\">home</a></button>");
            response.sendRedirect("ShowCartController?do=checkoutsuccess");
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
