/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOCategory;
import DAO.DAOProduct;
import entity.Cart;
import entity.Category;
import entity.Account;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;

/**
 *
 * @author ngock
 */
public class HomeController extends HttpServlet {

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
            if (service.equals("home")) {
                final int page_size = 9;
                //Get Date                        
                List<Category> Category = new DAOCategory().getAllCategory();
                HttpSession session = request.getSession();
                session.setAttribute("ListCategory", Category);
                //cat trang                            
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                DAOProduct dao = new DAOProduct();
                List<Products> Product = dao.getAllProductsWithPaging(page, page_size);
                int totalProduct = dao.getTotalProduct();
                int totalPage = totalProduct / page_size;
                if (totalProduct % page_size != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("ListProduct", Product);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (service.equals("ListCategory")) {
                final int page_size = 6;
                String cid = request.getParameter("cid");
                int id = Integer.parseInt(cid);
                HttpSession session = request.getSession();
                int page = 1;
                String pageStr = request.getParameter("page");

                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                DAOProduct dao = new DAOProduct();
                List<Products> Product = dao.getProductsWithPagingbtId(id, page, page_size);
                int totalProduct = dao.getTotalProductbyId(id);
                int totalPage = totalProduct / page_size;
                if (totalProduct % page_size != 0) {
                    totalPage += 1;
                }
                request.setAttribute("cid", cid);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("ListProduct", Product);
                request.getRequestDispatcher("ListCategory.jsp").forward(request, response);
            }
            if (service.equals("search")) {
                DAOProduct dao = new DAOProduct();
                final int page_size = 6;
                String key = request.getParameter("key");
                String submit = request.getParameter("submit");
                String sort = request.getParameter("sort");
                String from = request.getParameter("from");
                String to = request.getParameter("To");
//                double From = 0;
//                double To = 0;
//                if (from != null && to !=null) {
//                    From = Double.parseDouble(from);
//                    To = Double.parseDouble(to);
//                }

                if (sort.equals("2")) {
                    List<Products> Product = dao.getProductbyPrice(from, to);
                    request.setAttribute("ListProduct", Product);
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                }
                if (sort.equals("1")) {
//                    int page = 1;
//                    String pageStr = request.getParameter("page");
//                    if (pageStr != null) {
//                        page = Integer.parseInt(pageStr);
//                    }

                    List<Products> Product = dao.getProductbyName(key);
                    // int totalProduct = dao.getTotalProductbyName(key);
//                    int totalPage = totalProduct / page_size;
//                    if (totalProduct % page_size != 0) {
//                        totalPage += 1;
//                    }
                    // List<Category> Category = new DAOCategory().getAllCategory();
                    // request.setAttribute("key", key);
//                    request.setAttribute("page", page);
//                    request.setAttribute("totalPage", totalPage);
                    // request.setAttribute("ListCategory", Category);
                    request.setAttribute("ListProduct", Product);
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                }
                //                if (submit == null || key.isEmpty()) {
//                    response.sendRedirect("HomeController");
//                } else {
//                    int page = 1;    
//                    String pageStr = request.getParameter("page");
//                    if (pageStr != null) {
//                        page = Integer.parseInt(pageStr);
//                    }
//                    
//                    List<Products> Product = dao.getProductsWithPagingbtname(key, page, page_size);
//                    int totalProduct = dao.getTotalProductbyName(key);
//                    int totalPage = totalProduct / page_size;
//                    if (totalProduct % page_size != 0) {
//                        totalPage += 1;
//                    }
//                    }
//                    List<Category> Category = new DAOCategory().getAllCategory();
//                    request.setAttribute("key", key);
//                    request.setAttribute("page", page);
//                     request.setAttribute("totalPage", totalPage);
//                    request.setAttribute("ListCategory", Category);
//                    request.setAttribute("ListProduct", Product);
//                    request.getRequestDispatcher("search.jsp").forward(request, response); 

            }
            if (service.equals("checkout")) {
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
                        session.setAttribute(key, pro);
                    }
                }
                Account acc = (Account) session.getAttribute("accountcus");
                String cusid = acc.getCustomerid();
                request.setAttribute("cusid", cusid);
                //tinh tong tien:
                double totalMoney = 0;
                for (Cart list : listProductCarts) {
                    totalMoney += list.getPrice() * list.getQuantity();
                }
                request.setAttribute("totalMoney", totalMoney);
                request.setAttribute("listProductCarts", listProductCarts);
                request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
