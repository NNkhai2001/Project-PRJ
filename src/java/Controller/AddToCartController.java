/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOProduct;
import entity.Cart;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
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
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

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
                service = "home";
            }
            //AddToCart index
            if (service.equals("home")) {
                DAOProduct dao = new DAOProduct();
                String key = request.getParameter("pid");
                String PageHome = request.getParameter("page");
                int unitstock = Integer.parseInt(request.getParameter("unitstock"));
                HttpSession session = request.getSession();
                //get pro
                Cart proCart = (Cart) session.getAttribute(key);
                Products product = dao.getProductByKey(key);
                //not found
                if (unitstock > 0) {
                    String sizeStr;
                    try {
                        sizeStr = session.getAttribute("size").toString();
                    } catch (Exception e) {
                        sizeStr = null;
                    }
                    int size = 0;
                    if (sizeStr == null) {
                        size = 0;
                    } else {
                        size = Integer.parseInt(sizeStr);
                    }

                    if (proCart == null) {

                        int quantity = 1;
                        proCart = Cart.builder()
                                .productid(Integer.parseInt(key))
                                .productname(product.getProductname())
                                .quantity(quantity)
                                .price(product.getUnitprice())
                                .build();
                        session.setAttribute(key, proCart);
                        size++;
                        session.setAttribute("size", size);

                    } else {
                        int count = proCart.getQuantity() + 1;
                        proCart.setQuantity(count);

                    }
                }
                request.getRequestDispatcher("HomeController?page=" + PageHome).forward(request, response);
            }

            //AddToCart Listcategory
            if (service.equals("ListCategory")) {
                DAOProduct dao = new DAOProduct();
                String cid = request.getParameter("cid");
                String key = request.getParameter("pid");
                String PageHome = request.getParameter("page");
                int unitstock = Integer.parseInt(request.getParameter("unitstock"));
                HttpSession session = request.getSession();
                //get pro
                Cart proCart = (Cart) session.getAttribute(key);
                Products product = dao.getProductByKey(key);
                //not found
                if (unitstock > 0) {
                    String sizeStr;
                    try {
                        sizeStr = session.getAttribute("size").toString();
                    } catch (Exception e) {
                        sizeStr = null;
                    }
                    int size = 0;
                    if (sizeStr == null) {
                        size = 0;
                    } else {
                        size = Integer.parseInt(sizeStr);
                    }

                    if (proCart == null) {

                        int quantity = 1;
                        proCart = Cart.builder()
                                .productid(Integer.parseInt(key))
                                .productname(product.getProductname())
                                .quantity(quantity)
                                .price(product.getUnitprice())
                                .build();
                        session.setAttribute(key, proCart);
                        size++;
                        session.setAttribute("size", size);

                    } else {
                        int count = proCart.getQuantity() + 1;
                        proCart.setQuantity(count);

                    }
                }
                request.getRequestDispatcher("HomeController?do=ListCategory&cid=" + cid + "&page=" + PageHome).forward(request, response);
            }

            //AddToCart Search
            if (service.equals("search")) {
                DAOProduct dao = new DAOProduct();
                String search = request.getParameter("key1");
                String key = request.getParameter("pid");
                String PageHome = request.getParameter("page");
                int unitstock = Integer.parseInt(request.getParameter("unitstock"));
                HttpSession session = request.getSession();
                //get pro
                Cart proCart = (Cart) session.getAttribute(key);
                Products product = dao.getProductByKey(key);
                //not found
                if (unitstock > 0) {
                    String sizeStr;
                    try {
                        sizeStr = session.getAttribute("size").toString();
                    } catch (Exception e) {
                        sizeStr = null;
                    }
                    int size = 0;
                    if (sizeStr == null) {
                        size = 0;
                    } else {
                        size = Integer.parseInt(sizeStr);
                    }

                    if (proCart == null) {

                        int quantity = 1;
                        proCart = Cart.builder()
                                .productid(Integer.parseInt(key))
                                .productname(product.getProductname())
                                .quantity(quantity)
                                .price(product.getUnitprice())
                                .build();
                        session.setAttribute(key, proCart);
                        size++;
                        session.setAttribute("size", size);

                    } else {
                        int count = proCart.getQuantity() + 1;
                        proCart.setQuantity(count);
                    }
                }
                request.getRequestDispatcher("HomeController?do=search&key=" + search + "&submit=" + 1 + "&page=" + PageHome).forward(request, response);

            }

            //AddToCart Details
            if (service.equals("details")) {
                DAOProduct dao = new DAOProduct();
                String key = request.getParameter("pid");
                int unitstock = Integer.parseInt(request.getParameter("unitstock"));
                HttpSession session = request.getSession();
                //get pro
                Cart proCart = (Cart) session.getAttribute(key);
                Products product = dao.getProductByKey(key);
                //not found
                if (unitstock > 0) {
                    String sizeStr;
                    try {
                        sizeStr = session.getAttribute("size").toString();
                    } catch (Exception e) {
                        sizeStr = null;
                    }
                    int size = 0;
                    if (sizeStr == null) {
                        size = 0;
                    } else {
                        size = Integer.parseInt(sizeStr);
                    }

                    if (proCart == null) {

                        int quantity = 1;
                        proCart = Cart.builder()
                                .productid(Integer.parseInt(key))
                                .productname(product.getProductname())
                                .quantity(quantity)
                                .price(product.getUnitprice())
                                .build();
                        session.setAttribute(key, proCart);
                        size++;
                        session.setAttribute("size", size);

                    } else {
                        int count = proCart.getQuantity() + 1;
                        proCart.setQuantity(count);

                    }
                }
                request.getRequestDispatcher("DetailController?pid=" + key).forward(request, response);
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
