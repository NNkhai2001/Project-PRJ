/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOCustomer;
import DAO.DAOLogin;
import entity.Account;
import entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
                service = "login";
            }
            if (service.equals("login")) {
                HttpSession session = request.getSession();
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                } else {
                    String userName = request.getParameter("username");
                    String password = request.getParameter("password");
                    DAOLogin dao = new DAOLogin();
                    int check = dao.checkLogin(userName, password);
                    if (check == 2) {//Login Fail
                        request.setAttribute("mess", "wrong user or pass");
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    } else if (check == 0) {//Login Successfull   
                        DAOCustomer daocus = new DAOCustomer();
                        Customers cusid = daocus.getCusIDByUserName(userName);
                        session.setAttribute("accountcus", Account.builder()
                                .username(userName)
                                .password(password)
                                .Customerid(cusid.getCustomerid())
                                .build());

                        response.sendRedirect("HomeController");
                    } else {
                        session.setAttribute("accountemp", Account.builder()
                                .username(userName)
                                .password(password)
                                .build());

                        response.sendRedirect("AdminController");
                    }
                }
            }
            if (service.equals("logout")) {
                HttpSession session = request.getSession();
                session.removeAttribute("accountcus");
                response.sendRedirect("HomeController");
            }
            if (service.equals("updateprofile")) {
                DAOCustomer dao = new DAOCustomer();
                String submit = request.getParameter("submit");
                if (submit == null) {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("accountcus");
                    String cusID = acc.getCustomerid();
                    ResultSet rs = dao.getData("select * from Customers  where CustomerID='" + cusID + "'");
                    request.setAttribute("rsCustomer", rs);
                    request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
                } else {
                    String cID = request.getParameter("cId");
                    String cpName = request.getParameter("cpName");
                    String ctName = request.getParameter("ctName");
                    String ctTitle = request.getParameter("contacTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String pscode = request.getParameter("pscode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    Customers cus = Customers.builder()
                            .customerid(cID)
                            .companyname(cpName)
                            .contactname(ctName)
                            .contacttitle(ctTitle)
                            .address(address)
                            .city(city)
                            .region(region)
                            .postalcode(pscode)
                            .country(country)
                            .phone(phone)
                            .fax(fax)
                            .username(username)
                            .password(password)
                            .roll(0)
                            .build();
                    int n = dao.updateCustomers(cus);
                    String mess = "update Success!";
                    request.setAttribute("mess", mess);
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("accountcus");
                    String cusID = acc.getCustomerid();
                    ResultSet rs = dao.getData("select * from Customers  where CustomerID='" + cusID + "'");
                    request.setAttribute("rsCustomer", rs);
                    request.getRequestDispatcher("updateprofile.jsp").forward(request, response);
                }
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
