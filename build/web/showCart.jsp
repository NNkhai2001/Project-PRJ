<%-- 
    Document   : Index
    Created on : Mar 4, 2022, 12:25:32 PM
    Author     : ngock
--%>
<%@page import="java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h3>List Product</h3>
                <c:choose>
                    <c:when test="${mess eq mess1}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">ProductID</th>
                                    <th scope="col">ProductName</th>
                                    <th scope="col">Quantity</th> 
                                    <th scope="col">Price</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Remove</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${listProductCarts}" var="L">

                                <form method="post" action="ShowCartController?do=updatequantity">

                                    <tr>
                                    <input hidden="" value="${L.productid}" name="pid"/>
                                    <td scope="row">${L.productid}</td>
                                    <td>${L.productname}</td>
                                    <td><input onchange="this.form.submit()" type="number" value="${L.quantity}" name="quantity"></td>
                                    <td>${L.price}</td>
                                    <td>${L.price * L.quantity}</td>
                                    <td><a href="ShowCartController?do=delete&pid=${L.productid}" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Detele</a></td>    
                                    </tr>
                                </form>
                            </c:forEach>

                            </tbody>                   
                            <td colspan="5" ><h3>Total Amount:$${totalMoney}</h3></td>
                            <td colspan="5" ><a href="ShowCartController?do=delete" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Delete All</a></td>  

                        </table>    
                    </c:when>
                    <c:otherwise>
                        <%
                            ResultSet rsOrderList = (ResultSet) request.getAttribute("detailsOrderList");
                            double total = 0;
                        %>
                        <%if (rsOrderList.next()) {%>                
                        <table class="table">
                            <thead>
                                <tr>
                                    <th style="color: black">ProductID</th>
                                    <th style="color: black">ProductName</th>
                                    <th style="color: black">Quantity</th>
                                    <th style="color: black">UnitPrice</th>   
                                    <th style="color: black">total</th>
                                    <th style="color: black">Remove</th>
                                </tr>
                            </thead>
                            <%do {%>
                            <tbody>
                            <form method="post" action="BackToCard?do=updatequantity">
                                <tr>    
                                <input hidden="" value="<%=rsOrderList.getInt(6)%>" name="pid"/>
                                <input hidden="" value="<%=rsOrderList.getInt(10)%>" name="odId"/>
                                <input hidden="" value="<%=rsOrderList.getInt(6)%>"/>
                                <td style="color: black"><%=rsOrderList.getInt(6)%></td>
                                <td style="color: black"><%=rsOrderList.getString(7)%></td>
                                <td style="color: black"><input onchange="this.form.submit()" type="number" value="<%=rsOrderList.getInt(8)%>" name="quantity"></td>
                                <td style="color: black"><%=rsOrderList.getDouble(9)%></td>  
                                <td style="color: black"><%=rsOrderList.getDouble(4)%></td>
                                <td><a href="BackToCard?do=delete&pid=<%=rsOrderList.getInt(6)%>&odId=<%=rsOrderList.getInt(10)%>" class="btn btn-outline-danger"><i class="bi bi-trash"></i>Detele</a></td>    
                                </tr>
                            </form>
                            </tbody>
                            <%total += rsOrderList.getDouble(4);%>
                            <%} while (rsOrderList.next());%>
                        </table>
                        <%}%>
                        <h3>Total:<%=total%></h3>
                    </c:otherwise>
                </c:choose>
                <c:if test="${sessionScope. accountcus !=null}">
                    <a href="HomeController?do=checkout" class="btn btn-success w-15">Check Out</a>
                </c:if>
                <c:if test="${sessionScope.accountcus ==null}">
                    <a href="LoginController" class="btn btn-success w-15">Check Out</a>
                </c:if>                    
            </div>
        </section>

        <%@include file="components/FooterComponents.jsp" %>
    </body>

</html>