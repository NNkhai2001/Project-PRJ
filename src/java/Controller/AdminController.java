/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOCategory;
import DAO.DAOCustomer;
import DAO.DAOOrderList;
import DAO.DAOOrders;
import DAO.DAOProduct;
import entity.Category;
import entity.Customers;
import entity.OrderList;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ngock
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

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
                service = "admin";
            }
            if (service.equals("admin")) {
                DAOCustomer dao = new DAOCustomer();
                List<Customers> list = dao.listAllCustommer();
                request.setAttribute("list", list);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/AdminHome.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("UpdateCustomer")) {
                DAOCustomer dao = new DAOCustomer();
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String cusID = request.getParameter("cusid");
                    ResultSet rs = dao.getData("select * from Customers  where CustomerID='" + cusID + "'");
                    request.setAttribute("rsCustomer", rs);
                    request.getRequestDispatcher("AdminJSP/updateCustomer.jsp").forward(request, response);
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
                    System.out.println(cus);
                    int n = dao.updateCustomers(cus);
                    response.sendRedirect("AdminController?do=admin");
                }
            }

            if (service.equals("updateProduct")) {
                DAOProduct dao = new DAOProduct();
                String submit = request.getParameter("submit");
                if (submit == null) {
                    //Update
                    int pid = Integer.parseInt(request.getParameter("pId"));
                    ResultSet rs = dao.getData("select * from Products where ProductID=" + pid);
                    ResultSet rs1 = dao.getData("select * from Suppliers");
                    ResultSet rs2 = dao.getData("select * from Categories");

                    request.setAttribute("rsProduct", rs);
                    request.setAttribute("rsSupplier", rs1);
                    request.setAttribute("rsCategories", rs2);

                    RequestDispatcher dispath
                            = request.getRequestDispatcher("AdminJSP/updateProduct.jsp");
                    //runn
                    dispath.forward(request, response);
                } else {
                    //Step 2 update
                    String spid = request.getParameter("pid");
                    String ProductName = request.getParameter("pName");
                    String SupplierID = request.getParameter("suppId");
                    String CategoryID = request.getParameter("cateId");
                    String QuantityPerUnit = request.getParameter("qPerUnit");
                    String UnitPrice = request.getParameter("uPrice");
                    String UnitsInStock = request.getParameter("uInStock");
                    String UnitsOnOrder = request.getParameter("uOnOrder");
                    String ReorderLevel = request.getParameter("reOLevel");
                    String Discontinued = request.getParameter("discontinute");
                    String picture = request.getParameter("picture");

                    //Convert
                    int pid = Integer.parseInt(spid);
                    int suppId = Integer.parseInt(SupplierID);
                    int cateId = Integer.parseInt(CategoryID);
                    double uPrice = Double.parseDouble(UnitPrice);
                    int uInStock = Integer.parseInt(UnitsInStock);
                    int uOnOrder = Integer.parseInt(UnitsOnOrder);
                    int reOrLevel = Integer.parseInt(ReorderLevel);
                    int discon = Integer.parseInt(Discontinued);
                    //Display
                    Products pro = Products.builder()
                            .productid(pid)
                            .productname(ProductName)
                            .supplierid(suppId)
                            .categoryid(cateId)
                            .quantityperunit(QuantityPerUnit)
                            .unitprice(uPrice)
                            .unitsinstock(uInStock)
                            .unitsonorder(uOnOrder)
                            .reorderlevel(reOrLevel)
                            .discontinued(discon)
                            .picture(picture)
                            .build();
                    System.out.println(pro);
                    int n = dao.updateProducts(pro);
                    response.sendRedirect("AdminController?do=ListProduct");
                }
            }

            if (service.equals("ListProduct")) {
                DAOProduct dao = new DAOProduct();
                List<Products> list = dao.getAllProduct();
                request.setAttribute("list", list);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/ListProduct.jsp");
                dispath.forward(request, response);
            }
            if (service.equals("ListBill")) {
                DAOOrderList dao = new DAOOrderList();
                List<OrderList> list = dao.listAllOrderList();
                request.setAttribute("list", list);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/ListOrderList.jsp");
                dispath.forward(request, response);

            }
            if (service.equals("updateStatus")) {
                //String details = request.getParameter("details");
                String id = request.getParameter("odId");
                //Convert
                int odID = Integer.parseInt(id);
                int Status = Integer.parseInt(request.getParameter("status"));
                DAOOrderList dao = new DAOOrderList();
                int n = dao.updateStatus(Status, odID);
                response.sendRedirect("AdminController?do=ListBill");
            }
            if (service.equals("updateStatusDetails")) {
                //String details = request.getParameter("details");
                String id = request.getParameter("odId");
                //Convert
                int odID = Integer.parseInt(id);
                int Status = Integer.parseInt(request.getParameter("status"));
                DAOOrderList dao = new DAOOrderList();
                int n = dao.updateStatus(Status, odID);
                ResultSet rs = dao.getData("select cus.ContactName,o.ShipAddress,o.OrderDate,(od.Quantity * od.UnitPrice)'total',o.[status] ,pro.ProductID,pro.ProductName,od.Quantity,od.UnitPrice,o.OrderID  from [Order Details] od\n"
                        + "                                                                 join Orders o on o.OrderID = od.OrderID\n"
                        + "                                                                  join Products pro on od.ProductID = pro.ProductID\n"
                        + "                                                                 join Suppliers sup on pro.SupplierID = sup.SupplierID\n"
                        + "                                                                 join Categories cate on pro.CategoryID = cate.CategoryID\n"
                        + "                                                                  join Customers cus on o.CustomerID = cus.CustomerID\n"
                        + "                                                                 join Employees emp on o.EmployeeID = emp.EmployeeID\n"
                        + "                                                                  join Shippers ship on o.ShipVia = ship.ShipperID \n"
                        + "                                                                 where o.OrderID=" + odID);

                request.setAttribute("detailsOrderList", rs);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/detailsOrderList.jsp");
                dispath.forward(request, response);
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
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/detailsOrderList.jsp");
                dispath.forward(request, response);
            }

            if (service.equals("CreateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    DAOProduct dao = new DAOProduct();
                    ResultSet rs1 = dao.getData("select * from Suppliers");
                    ResultSet rs2 = dao.getData("select * from Categories");
                    request.setAttribute("rsSupplier", rs1);
                    request.setAttribute("rsCategories", rs2);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("AdminJSP/CreateProduct.jsp");
                    dispath.forward(request, response);
                } else {
                    DAOProduct dao = new DAOProduct();
                    ResultSet rs1 = dao.getData("select * from Suppliers");
                    ResultSet rs2 = dao.getData("select * from Categories");
                    request.setAttribute("rsSupplier", rs1);
                    request.setAttribute("rsCategories", rs2);
                    String ProductName = request.getParameter("pName");
                    String SupplierID = request.getParameter("suppId");
                    String CategoryID = request.getParameter("cateId");
                    String QuantityPerUnit = request.getParameter("qPerUnit");
                    String UnitPrice = request.getParameter("uPrice");
                    String UnitsInStock = request.getParameter("uInStock");
                    String UnitsOnOrder = request.getParameter("uOnOrder");
                    String ReorderLevel = request.getParameter("reOLevel");
                    String Discontinued = request.getParameter("discontinute");
                    String picture = request.getParameter("picture");
                    //Check/ Validate data
                    if (ProductName == null || ProductName.isEmpty()) {
                        out.print("<h3 style='color:red'>ProductName cannot Null </h3>");
                        return;
                    }
                    if (ProductName.length() > 40) {
                        out.print("<h3 style='color:red'>ProductName must <= 40 char </h3>");
                        return;
                    }
                    if (QuantityPerUnit.length() > 20) {
                        out.print("<h3 style='color:red'>QuantityPerUnit must <= 20 char </h3>");
                        return;
                    }
                    if (Double.parseDouble(UnitPrice) < 0) {
                        out.print("<h3 style='color:red'>UnitPrice must >=0  </h3>");
                        return;
                    }
                    if (Integer.parseInt(ReorderLevel) < 0) {
                        out.print("<h3 style='color:red'>ReorderLevel must >=0  </h3>");
                        return;
                    }
                    if (Integer.parseInt(UnitsInStock) < 0) {
                        out.print("<h3 style='color:red'>UnitsInStock must >=0  </h3>");
                        return;
                    }
                    if (Integer.parseInt(UnitsOnOrder) < 0) {
                        out.print("<h3 style='color:red'>UnitsOnOrder must >=0  </h3>");
                        return;
                    }
                    //Convert
                    int suppId = Integer.parseInt(SupplierID);
                    int cateId = Integer.parseInt(CategoryID);
                    double uPrice = Double.parseDouble(UnitPrice);
                    int uInStock = Integer.parseInt(UnitsInStock);
                    int uOnOrder = Integer.parseInt(UnitsOnOrder);
                    int reOrLevel = Integer.parseInt(ReorderLevel);
                    int discon = Integer.parseInt(Discontinued);
                    //Display
                    Products pro = Products.builder()
                            .productname(ProductName)
                            .supplierid(suppId)
                            .categoryid(cateId)
                            .quantityperunit(QuantityPerUnit)
                            .unitprice(uPrice)
                            .unitsinstock(uInStock)
                            .unitsonorder(uOnOrder)
                            .reorderlevel(reOrLevel)
                            .discontinued(discon)
                            .picture(picture)
                            .build();
                    int n = dao.addProducts(pro);
                    if (n > 0) {
                        String mess = "Add Success!";
                        request.setAttribute("mess", mess);
                        RequestDispatcher dispath
                                = request.getRequestDispatcher("AdminJSP/CreateProduct.jsp");
                        dispath.forward(request, response);
                    }

                }
            }
            if (service.equals("CreateCategory")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("AdminJSP/CreateCategory.jsp");
                    dispath.forward(request, response);
                } else {
                    DAOCategory dao = new DAOCategory();
                    String cateName = request.getParameter("ctName");
                    String des = request.getParameter("description");
                    //Check/Validate
                    if (cateName == null || cateName.equals("")) {
                        out.print("CategoryName is not empty");
                    }
                    if (cateName.length() > 15) {
                        out.print("<h3 style='color:red'>CategoryName must <= 15 char </h3>");
                        return;
                    }
                    Category cate = Category.builder()
                            .categoryname(cateName)
                            .description(des)
                            .build();
                    int n = dao.addCategories(cate);
                    if (n > 0) {
                        String mess = "Add Success!";
                        request.setAttribute("mess", mess);
                        RequestDispatcher dispath
                                = request.getRequestDispatcher("AdminJSP/CreateCategory.jsp");
                        dispath.forward(request, response);
                    }
                }
            }
            if (service.equals("deleteCustomer")) {
                DAOCustomer dao = new DAOCustomer();
                String id = request.getParameter("cusid");
                List list = dao.getCusId();
                int count = 0;
                for (Object object : list) {
                    if (id.equals(object)) {
                        count = 1;
                    }
                }
                if (count == 1) {
                    dao.deleteCustomer(id);
                    String mess = "Delete Success!";
                    request.setAttribute("mess", mess);
                    List<Customers> listA = dao.listAllCustommer();
                    request.setAttribute("list", listA);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("AdminJSP/AdminHome.jsp");
                    dispath.forward(request, response);
                } else {
                    String mess = "Delete Fail!";
                    request.setAttribute("mess", mess);
                    List<Customers> listA = dao.listAllCustommer();
                    request.setAttribute("list", listA);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("AdminJSP/AdminHome.jsp");
                    dispath.forward(request, response);
                }
            }
            if (service.equals("deleteProduct")) {
                DAOProduct daoPro = new DAOProduct();
                DAOCustomer dao = new DAOCustomer();
                String id = request.getParameter("pId");

                List list = dao.getOderDetailsId();
                int count = 0;
                for (Object object : list) {
                    if (id.equals(object)) {
                        count = 1;
                    }
                }
                if (count == 1) {
                    dao.deleteProductIdInOrderDetail(id);
                    dao.deleteProduct(id);
                } else {
                    dao.deleteProduct(id);
                }
                String mess = "Delete Success!";
                request.setAttribute("mess", mess);
                List<Products> listA = daoPro.getAllProduct();
                request.setAttribute("list", listA);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/ListProduct.jsp");
                dispath.forward(request, response);
            }
            if(service.equals("searchstatus")) {
                DAOOrderList dao = new DAOOrderList();
                String id = request.getParameter("status");
                if(id.equals("-1")) {
                    List<OrderList> list = dao.listAllOrderList();
                request.setAttribute("list", list);
                request.setAttribute("id", id);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/ListOrderList.jsp");
                dispath.forward(request, response);
                }else {
                    List<OrderList> list = dao.listAllOrderListbystatus(id);
                    request.setAttribute("list", list);
                    request.setAttribute("id", id);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/ListOrderList.jsp");
                dispath.forward(request, response);
                }
            }
            if(service.equals("deleteStatus")) {
                String odId = request.getParameter("odId");
                DAOOrderList dao = new DAOOrderList();
                List list = dao.getOderIDinOrderDetails();
                int count =0;
                for (Object object : list) {
                    if(odId.equals(object)) {
                        count=1;
                    }
                }
                if(count ==1) {
                    dao.deleteOderIDInOrderDetail(odId);
                    dao.deleteStatus(odId);
                } else dao.deleteStatus(odId);
                String mess = "DeleteSuccess!";
                List<OrderList> listA = dao.listAllOrderList();
                request.setAttribute("list", listA);
                request.setAttribute("mess", mess);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("AdminJSP/ListOrderList.jsp");
                dispath.forward(request, response);
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
