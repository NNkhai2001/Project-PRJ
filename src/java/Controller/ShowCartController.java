/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.var;

/**
 *
 * @author ngock
 */
@WebServlet(name = "ShowCartController", urlPatterns = {"/ShowCartController"})
public class ShowCartController extends HttpServlet {

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
                service = "showcart";
            }
            if (service.equals("showcart")) {
                List<Cart> listProductCarts = new ArrayList<>();
                HttpSession session = request.getSession();
                Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("urlHistory") && !key.equals("ListCategory") && !key.equals("accountcus") && !key.equals("size") && !key.equals("accountemp")) {
                        Cart pro = (Cart) session.getAttribute(key);
                        if (pro == null) {
                            listProductCarts = new ArrayList<>();
                        }
                        listProductCarts.add(pro);
                        session.setAttribute(key,  pro);
                    }
                }
                //tinh tong tien:
                double totalMoney = 0;
                for (Cart list : listProductCarts) {
                    totalMoney += list.getPrice() * list.getQuantity();
                }
                totalMoney = Math.round(totalMoney * 100) / 100;
                String mess = "ShowCart";
                String mess1 = "ShowCart";
                request.setAttribute("mess", mess);
                request.setAttribute("mess1", mess1);
                request.setAttribute("totalMoney", totalMoney);
                request.setAttribute("listProductCarts", listProductCarts);
                request.getRequestDispatcher("showCart.jsp").forward(request, response);
            }
            if (service.equals("delete")) {
                String pid = request.getParameter("pid");
                HttpSession session = request.getSession();
                if (pid != null) {
                    String size = session.getAttribute("size").toString();
                    int sizeint = Integer.parseInt(size) - 1;
                    String Size = Integer.toString(sizeint);
                    session.setAttribute("size", Size);
                    session.removeAttribute(pid);
                } else {
                    Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String key = em.nextElement().toString();
                        if (!key.equals("urlHistory") && !key.equals("ListCategory") && !key.equals("accountcus") && !key.equals("accountemp") && !key.equals("Page")) {
                            session.removeAttribute(key);
                        }

                    }
                }
                response.sendRedirect("ShowCartController");
            }
            if (service.equals("updatequantity")) {
                String pid = request.getParameter("pid");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                HttpSession session = request.getSession();
                Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (key.contains(pid)) {
                        Cart pro = (Cart) session.getAttribute(key);
                        pro.setQuantity(quantity);
                        request.setAttribute("listProductCarts", pro);
                    }
                }
                response.sendRedirect("ShowCartController");
            }
            if (service.equals("updatequantityBack")) {
                String odId = request.getParameter("odId");
                
                request.setAttribute("odId", odId);
                request.getRequestDispatcher("BackToCard").forward(request, response);
            }
            if (service.equals("checkoutsuccess")) {
                HttpSession session = request.getSession();
                Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String key = em.nextElement().toString();
                    if (!key.equals("urlHistory") && !key.equals("ListCategory") && !key.equals("accountcus") && !key.equals("accountemp") && !key.equals("Page")) {
                        session.removeAttribute(key);
                    }

                }
                response.sendRedirect("DirectCardController");
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
