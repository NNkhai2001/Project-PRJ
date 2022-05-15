<%-- 
    Document   : newjsp
    Created on : Mar 14, 2022, 2:52:08 PM
    Author     : ngock
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="entity.Products"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Admin</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="AdminController">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3"> HE151295-Nguyen Ngoc Khai </div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Divider -->
                <hr class="sidebar-divider">


                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-fw fa-cog"></i>
                        <span>List</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <a class="collapse-item" href="AdminController">Customer Manager</a>
                            <a class="collapse-item" href="AdminController?do=ListProduct">Product Manager</a>
                            <a class="collapse-item" href="AdminController?do=ListBill">Bill Manager</a>
                        </div>
                    </div>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fas fa-fw fa-wrench"></i>
                        <span>Create</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Create</h6>
                            <a class="collapse-item" href="AdminController?do=CreateProduct">Create Product</a>
                            <a class="collapse-item" href="AdminController?do=CreateCategory">Create Category</a>

                        </div>
                    </div>
                </li>
                =

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">


                        <!-- Topbar Search -->
                        <form
                            class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                       aria-label="Search" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">


                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.accountemp.username}</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">                                                               
                                    <a class="dropdown-item" href="LoginController" >                                   
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">



                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Update Product</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <body>
                                       
                                        
                                        <form action="AdminController?do=updateProduct" method="post">                       
                                        <%
                                            ResultSet rsProduct = (ResultSet) request.getAttribute("rsProduct"),
                                                    rsSuppliers = (ResultSet) request.getAttribute("rsSupplier"),
                                                    rsCategories = (ResultSet) request.getAttribute("rsCategories");
                                        %>
                                        <%if (rsProduct.next()) {%>
                                            <table>
                                                <tr>
                                                    <td>ProductID</td>
                                                    <td><input type="text" value="<%=rsProduct.getInt(1)%>" name="pid"></td>
                                                </tr>
                                                <tr>
                                                    <td>ProductName</td>
                                                    <td><input type="text" value="<%=rsProduct.getString(2)%>" name="pName"></td>
                                                </tr>
                                                <tr>
                                                    <td><label for="suppId">SupplierID</label></td>
                                                    <td>
                                                        <select name="suppId" id="suppId">
                                                            <%while (rsSuppliers.next()) {%>
                                                            <option value="<%=rsSuppliers.getInt(1)%>"><%=rsSuppliers.getString(2)%></option>  

                                                            <%}%>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><label for="cateId">CategoryID</label></td>
                                                    <td>    
                                                        <select name="cateId" id="cateId">
                                                            <option value="1">Beverages</option>
                                                            <option value="2">Condiments</option>
                                                            <option value="3">Confections</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>QuantityPerUnit</td>
                                                    <td><input type="text" value="<%=rsProduct.getString(5)%>" name="qPerUnit"></td>
                                                </tr>
                                                <tr>
                                                    <td>UnitPrice</td>
                                                    <td><input type="number" value="<%=rsProduct.getDouble(6)%>" name="uPrice"></td>
                                                </tr>
                                                <tr>
                                                    <td>UnitsInStock</td>
                                                    <td><input type="number" value="<%=rsProduct.getInt(7)%>" name="uInStock"></td>
                                                </tr>
                                                <tr>
                                                    <td>UnitsOnOrder</td>
                                                    <td><input type="number" value="<%=rsProduct.getInt(8)%>" name="uOnOrder"></td>
                                                </tr>
                                                <tr>
                                                    <td>ReorderLevel</td>
                                                    <td><input type="number" value="<%=rsProduct.getInt(9)%>" name="reOLevel"></td>
                                                </tr>
                                                <tr>
                                                    <td>Discontinued</td>
                                                    <td><input type="radio" value="0" <%=(rsProduct.getInt(10) == 0 ? " checked" : "")%> name="discontinute">Continute
                                                        <input type="radio" value="1" <%=(rsProduct.getInt(10) == 1 ? " checked" : "")%> name="discontinute">Discontinute</td>
                                                </tr>
                                                <tr>
                                                    <td>Picture</td>
                                                    <td><input type="text" value="<%=rsProduct.getString(11)%>" name="picture"></td>
                                                </tr>
                                                <tr>
                                                    <td><input type="submit" value="Update" name="submit"></td>
                                                    <td><input type="reset" value="Clear"></td>
                                                </tr>
                                            </table>
                                        </form>
                                        <%}%>
                                    </body>

                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2020</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>

    </body>

</html>