<%-- 
    Document   : DirectCard
    Created on : Mar 17, 2022, 12:42:39 PM
    Author     : ngock
--%>

<%@page import="java.sql.ResultSet"%>
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
        <!-- Header-->
        <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">       
                    <img style="width: 249px;height: 268px;" src="img/Lenovo-870x260.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img style="width: 249px;height: 268px;" src="img/slider2.png" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img style="width: 249px;height: 268px;" src="img/slider-laptop.jpg" class="d-block w-100" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!-- Section-->
        <section class="py-5" style="background-image: url(https://ak.picdn.net/shutterstock/videos/9025156/thumb/1.jpg)">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row">
                </div>
                <body> 
                    <%
                        ResultSet rsOrderList = (ResultSet) request.getAttribute("detailsOrderList");
                    %>
                    <%if (rsOrderList.next()) {%>
                    <p><h3 style="color: red" ><b> Information Customer:  </b></h3></p> 
                    <p style="margin-left: 40%"<p style="color: black"><b>Customer Name: </b><%=rsOrderList.getString(1)%></p> </p>
                    <p style="margin-left: 40%"   <p style="color: black"><b>Address: </b><%=rsOrderList.getString(2)%></p> 
                    <p style="margin-left: 40%"  <p style="color: black"><b>Shipper Date: </b><%=rsOrderList.getString(3)%></p> 
                    <p style="margin-left: 40%"  <p style="color: black"><b>Status:
                            <%=rsOrderList.getInt(5) == 1 ? "New" : ""%>
                            <%=rsOrderList.getInt(5) == 2 ? "Process" : ""%>
                            <%=rsOrderList.getInt(5) == 3 ? "Done" : ""%>
                        </b></p>
                    <p><h3 style="color: red"><b>Details:  </b></h3></p>                                       
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th style="color: black">ProductID</th>
                                <th style="color: black">ProductName</th>
                                <th style="color: black">Quantity</th>
                                <th style="color: black">UnitPrice</th>   
                                <th style="color: black">total</th>                   
                            </tr>
                        </thead>
                        <%do {%>
                        <tbody>

                            <tr>
                                <td style="color: black"><%=rsOrderList.getInt(6)%></td>
                                <td style="color: black"><%=rsOrderList.getString(7)%></td>
                                <td style="color: black"><%=rsOrderList.getInt(8)%></td>
                                <td style="color: black"><%=rsOrderList.getDouble(9)%></td>  
                                <td style="color: black"><%=rsOrderList.getDouble(4)%></td>
                            </tr>                   
                        </tbody>
                        <%} while (rsOrderList.next());%>
                    </table>
                    <%}%>   
                </body>

            </div>
        </section>
        <!-- Footer-->
        <%@include file="components/FooterComponents.jsp" %>
