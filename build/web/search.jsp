<%-- 
    Document   : Index
    Created on : Mar 4, 2022, 12:25:32 PM
    Author     : ngock
--%>

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
                    <div class="col-md-12 ">
                        <h3>List Categories</h3>  
                    </div>

                    <c:forEach items="${ListCategory}" var="L">       
                        <div class="col-md-3 ">                     
                            <strong class="list-group-item ${L.categoryid==cid?"active":""}" style="background-color: yellow; text-align: center" ><a href="HomeController?do=ListCategory&cid=${L.categoryid}">${L.categoryname}</a></strong>                     
                        </div>

                    </c:forEach>    
                    <div class="col-md-12">
                        <h3>List Products</h3>
                        <c:choose>
                            <c:when test="${ListProduct==null || ListProduct.size()==0}"> <!-- tuong duong if-->
                                not founds
                            </c:when>
                            <c:otherwise><!-- tuong duong else-->
                                <nav aria-label="Page navigation example mx-auto">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ">
                                            <c:if test="${page != 1}">
                                                <a class="page-link" href="HomeController?do=search&page=${page-1}&key=${key}&submit=${1}">Previous</a>
                                            </c:if>
                                        </li>
                                        <c:forEach begin="1" end="${totalPage}" var="i">                                                                   
                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="HomeController?do=search&key=${key}&page=${i}&submit=${1}">${i}</a></li>
                                            </c:forEach>  
                                        <li class="page-item">
                                            <c:if test="${page != totalPage}">
                                                <a class="page-link" href="HomeController?do=search&page=${page+1}&key=${key}&submit=${1}">Next</a>
                                            </c:if>
                                        </li>
                                    </ul>
                                </nav>
                            </c:otherwise>
                        </c:choose>
                        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 justify-content-center">
                            <c:forEach items="${ListProduct}" var="L">
                                <div class="col mb-5">
                                    <div class="card h-100">
                                        <!-- Sale badge-->
                                        <div class="badge bg-dark text-white position-absolute"
                                             style="top: 0.5rem; right: 0.5rem">Sale</div>
                                        <!-- Product image-->
                                        <a href="DetailController?pid=${L.productid}">
                                            <img class="card-img-top" src="${L.picture}"
                                                 alt="..." />
                                        </a>
                                        <!-- Product details-->
                                        <div class="card-body p-4">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <h5 class="fw-bolder">${L.productname}</h5>
                                                <!-- Product reviews-->
                                                <div class="d-flex justify-content-center small text-warning mb-2">
                                                    <div class="bi-star-fill"></div>
                                                    <div class="bi-star-fill"></div>
                                                    <div class="bi-star-fill"></div>
                                                    <div class="bi-star-fill"></div>
                                                    <div class="bi-star-fill"></div>
                                                </div>
                                                <!-- Product price-->
                                                <span class="text-muted text-decoration-line-through">$1000</span>
                                                $${L.unitprice}
                                            </div>
                                        </div>
                                        <!-- Product actions-->
                                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                            <c:if test="${L.unitsinstock > 0}">
                                                <div  class="text-center"><a class="btn btn-outline-dark mt-auto" href="AddToCartController?do=search&pid=${L.productid}&unitstock=${L.unitsinstock}&key1=${key}&page=${page}">Add to cart</a></div>                                            
                                            </c:if >
                                            <c:if test="${L.unitsinstock == 0}">
                                                <div class="text-center"><a id="Het hang" onclick="myfuntion(this.id)" class="btn btn-outline-dark mt-auto" href="HomeController?do=search&key=${key}&page=${page}&submit=${1}">Add to cart</a></div>                                            
                                            </c:if>


                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>

            </div>
        </section>
        <%@include file="components/FooterComponents.jsp" %>

        <script>
            function myfuntion(x) {
                alert(x + " quay lai sau nhe!");
            }
        </script>
    </body>

</html>