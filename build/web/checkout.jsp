<%-- 
    Document   : Index
    Created on : Mar 4, 2022, 12:25:32 PM
    Author     : ngock
--%>

<%@page import="DAO.DAOCustomer"%>
<%@page import="entity.Cart"%>
<%@page import="entity.Products"%>
<%@page import="java.util.List"%>
<%@page import="entity.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>

    <body>
        <%@include file="components/HeaderComponents.jsp" %>
        <!-- Product section-->
        <section class="py-5" >
            <div class="container " style="min-height: 600px">
                <h1>Check Out</h1>
                <div class="row">
                    <div class="col-md-8" style="border: 1px solid #ccc;border-radius: 5px;padding: 1rem">

                        <h3>List Product</h3>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ProductID</th>
                                    <th scope="col">ProductName</th>
                                    <th scope="col">Quantity</th> 
                                    <th scope="col">Price</th>
                                    <th scope="col">Total</th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${listProductCarts}" var="L">
                                <tr>
                            <input hidden="" value="${L.productid}" name="pid"/>
                            <td scope="row">${L.productid}</td>
                            <td>${L.productname}</td>
                            <td>${L.quantity}</td> 
                            <td>${L.price}</td>
                            <td>${L.price *L.quantity }</td>
                            </tr>
                            </c:forEach>
                            </tbody>                   
                            <td colspan="5" ><h2>Total Amount:$${totalMoney}</h2></td>


                        </table>
                            <a href="CheckOutController" method="post" class="btn btn-success w-15">Check Out</a>
                    </div>
                    <div class="col-md-4" style="border: 1px solid #ccc;border-radius: 5px; padding: 1rem">
                        <h3>Information of Customer</h3>
                        
                        <form action="CheckOutController" method="POST" >
                            <input type="hidden" name="do" value="checkout">
                            <div class="mb-3">
                                <label for="name" class="form-label">UserID</label>
                                <input class="form-control" id="name" name="CustomerID" value="${cusid}" aria-describedby="emailHelp" readonly="">
                            </div> 
                            <div class="mb-3">
                                <label for="total" class="form-label">Total</label>
                                <input type="text" class="form-control" id="phone" name="total" value="${totalMoney}" aria-describedby="emailHelp" readonly>
                            </div> 
                            <div class="mb-3">
                                <label for="address" class="form-label">Address</label>
                                <input type="text" class="form-control" id="address" name="address" aria-describedby="emailHelp">
                            </div>        
                            <div class="mb-3">
                                <label for="name" class="form-label">Status</label>
                                <input class="form-control" name="status" value="1" aria-describedby="emailHelp" readonly>
                            </div>   
                            <button   type="submit" class="btn btn-primary w-100" >Submit</button>
                        </form>


                    </div>

                    

                </div>
        </section>

        <%@include file="components/FooterComponents.jsp" %>
    </body>

</html>