/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAORegister;
import entity.Customers;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

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
            DAORegister dao = new DAORegister();
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                String username = request.getParameter("username");
                String pass = request.getParameter("password");
                String repass = request.getParameter("password2");
                String mess;
                if (pass.equals(repass)) {
                    boolean checkUsername = dao.checkSignUp(username, pass);
                    if (checkUsername == false) {
                        mess = "UserName exits!";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("Register.jsp").forward(request, response);
                    } else {
                        mess = "Register Successfull";
                        Account acc = Account.builder()
                                .username(username)
                                .password(pass)
                                .build();
                        session.setAttribute("accountcus", acc);
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                } else {
                    mess = "Password must same Repassword";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
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
