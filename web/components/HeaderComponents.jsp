<%-- 
    Document   : HeaderComponents
    Created on : Mar 4, 2022, 10:00:56 PM
    Author     : ngock
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light"> 
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" >HE151295-Nguyen Ngoc Khai</a>
    </div>

</nav>
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
    <div class="container px-4 px-lg-5">

        <a class="navbar-brand" href="">StoreComputer</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="HomeController">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="DirectCardController">Direct card</a></li>               
            </ul>
            <!-- Search -->
            
            <form action="HomeController?do=search" method="post" class="d-flex mx-auto">
                <select name="sort">
                    <option value="1">Name</option>
                    <option value="2">Price</option>
                </select>
               
                    <input class="form-control me-2" type="number" placeholder="From" aria-label="Search" name="from">
                  <input class="form-control me-2" type="number" placeholder="To" aria-label="Search" name="To">               
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="key">

                <button class="btn btn-outline-success" type="submit" name="submit">Search</button>
            </form> 
            <!-- Cart -->
            <div class="d-flex my-2">
                <a class="btn btn-outline-dark" href="ShowCartController">
                    <i class="bi-cart-fill me-1"></i>
                    Cart

                    <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.size==null ? "0" : sessionScope.size}</span>
                </a>
            </div>
            <!-- Login -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                       <c:if test="${sessionScope.accountcus ==null}">
                       data-bs-toggle="dropdown" aria-expanded="false"> <img src="https://cdn.iconscout.com/icon/free/png-256/account-avatar-profile-human-man-user-30448.png" height="40px" width="70%"/>
                           </c:if>
                           <c:if test="${sessionScope.accountcus !=null}">
                               data-bs-toggle="dropdown" aria-expanded="false"> <img src="https://cdn.iconscout.com/icon/free/png-256/account-avatar-profile-human-man-user-30448.png" height="30px" width="50%"/>${sessionScope.accountcus.username}
                           </c:if>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:if test="${sessionScope.accountcus ==null}">
                            <li><a class="dropdown-item" href="LoginController?do=login">Login</a></li>
                            <li><a class="dropdown-item" href="RegisterController">Regsiter</a></li>  
                        </c:if>
                        <c:if test="${sessionScope.accountcus !=null}">
                            <li><a class="dropdown-item" href="LoginController?do=logout">Logout</a></li>
                            <li><a class="dropdown-item" href="LoginController?do=updateprofile">UpdateProfile</a></li>
                        </c:if>
                    </ul>  
                </li>
            </ul>

        </div>
    </div>
</nav>
