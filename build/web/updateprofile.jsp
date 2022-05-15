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
                <h3>Update Profile</h3>
                <c:if test="${mess !=null}">
                    <h4>${mess}</h4>
                </c:if>
                <form action="LoginController?do=updateprofile" method="post">
                    <% ResultSet rsCus = (ResultSet) request.getAttribute("rsCustomer");%>
                    <%if (rsCus.next()) {%>
                    <div class="row g-3">
                        <div class="col">
                            <h6>CustomerID</h6>
                            <input type="text" name="cId" value="<%=rsCus.getString(1)%>" class="form-control" readonly="">
                        </div>
                        <div class="col">
                            <h6>CompanyName</h6>
                            <input type="text" name="cpName" value="<%=rsCus.getString(2)%>"  class="form-control" placeholder="CompanyName" aria-label="CompanyName">
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <h6>ContactName</h6>
                            <input type="text" name="ctName" value="<%=rsCus.getString(3)%>" class="form-control"  placeholder="ContactName" aria-label="ContactName">
                        </div>
                        <div class="col">
                            <h6>ContactTitle</h6>
                            <input type="text" name="contacTitle" value="<%=rsCus.getString(4)%>" class="form-control" placeholder="ContactTitle" aria-label="ContactTitle">
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <h6>Address</h6>
                            <input type="text" name="address" value="<%=rsCus.getString(5)%>" class="form-control" placeholder="Address" aria-label="Address">
                        </div>
                        <div class="col">
                            <h6>City</h6>
                            <input type="text" name="city" value="<%=rsCus.getString(6)%>" class="form-control" placeholder="City" aria-label="City">
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <h6>Region</h6>
                            <input type="text" name="region" value="<%=rsCus.getString(7)%>" class="form-control" placeholder="Region" aria-label="Region">
                        </div>
                        <div class="col">
                            <h6>PostalCode</h6>
                            <input type="text" name="pscode" value="<%=rsCus.getString(8)%>" class="form-control" placeholder="PostalCode" aria-label="PostalCode">
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <h6>Country</h6>
                            <input type="text" name="country" value="<%=rsCus.getString(9)%>" class="form-control" placeholder="Country" aria-label="Country">
                        </div>
                        <div class="col">
                            <h6>Phone</h6>
                            <input type="text" name="phone" value="<%=rsCus.getString(10)%>" class="form-control" placeholder="Phone" aria-label="Phone">
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <h6>Fax</h6>
                            <input type="text" name="fax" value="<%=rsCus.getString(11)%>" class="form-control" placeholder="Fax" aria-label="Fax">
                        </div>
                        <div class="col">
                            <h6>username</h6>
                            <input type="text" name="username" value="<%=rsCus.getString(12)%>" class="form-control" readonly="" >
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <h6>password</h6>
                            <input type="text" name="password" value="<%=rsCus.getString(13)%>" class="form-control" placeholder="password" aria-label="password">
                        </div>

                    </div>
                    <h4><input type="submit" value="Update" name="submit"></h4>
                    <h4><input type="reset" value="Clear"></h4>
                    <%}%>    
                </form>
            </div>
        </section>
        <!-- Footer-->
        <%@include file="components/FooterComponents.jsp" %>
