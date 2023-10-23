<%-- 
    Document   : cart
    Created on : 15-10-2023, 22:43:12
    Author     : VietBao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User"%>
<%@page import="model.Product"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">

        <title>Giftos | Accessories and Gifts</title>

        <!-- slider stylesheet -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet" />
    </head>
    <body>
        <%
            Product loginProduct = (Product) session.getAttribute("LIST_SEARCHPRODUCT");
            String search = request.getParameter("searchCart");
            if (search == null) {
                search = "";
            }
        %>
        <!-- hero area -->
        <div class="hero_area">
            <!-- header section strats -->
            <header class="header_section">
                <nav class="navbar navbar-expand-lg custom_nav-container ">
                    <a class="navbar-brand" href="home.jsp">
                        <span>
                            Giftos
                        </span>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class=""></span>
                    </button>
                    <div class="collapse navbar-collapse innerpage_navbar" id="navbarSupportedContent">                       
                        <ul class="navbar-nav  ">
                            <li class="nav-item ">
                                <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="DisplayController">
                                    Shop
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="why.jsp">
                                    Why Us
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="testimonial.jsp">
                                    Testimonial
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="contact.jsp">Contact Us</a>
                            </li>
                        </ul>                        
                        <div class="user_option"> 
                            <%
                                User loginUser = (User) session.getAttribute("LOGIN_USER");
                                if (loginUser == null) {
                                    loginUser = new User();
                                }
                                String name = loginUser.getFullname();
                                if (name == null) {
                            %>   
                            <a href="login.jsp">
                                <!--MainController?action=Login-->
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span>Login</span>
                            </a>
                            <%} else {%>
                            <a href="#">
                                <img  style="margin-left: 150px; border-radius: 100%" src="https://inkythuatso.com/uploads/thumbnails/800/2022/03/4a7f73035bb4743ee57c0e351b3c8bed-29-13-53-17.jpg" width="35px" height="35px"/>
                                <span><%=name%></span>  |
                                <a href="MainController?action=Logout">Logout</a>
                            </a>   
                            <%}%>  
                            <div class="nav-item active">
                                <a class="nav-link">
                                    Your Cart ${requestScope.sizeCart}
                                </a>                              
                            </div>
                        </div>
                    </div>
                </nav>
            </header>
            <!-- end header section -->
        </div>
        <!-- end hero area -->       

        <h1>Your Cart</h1> 
        <center>
            <table border="1">
                <tr>
                    <th>No</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>                
                    <th>Total</th>
                    <th>Action</th>
                </tr>
                <c:set var="o" value="${requestScope.Cart}"/>
                <c:set var="stt" value="0"/>
                <c:forEach items="${o.items}" var="i">
                    <c:set var="stt" value="${stt+1}"/>
                    <tr>                               
                        <td> ${stt} </td>
                        <td> ${i.product.title} </td>
                        <td> 
                            <button><a href="UpdateCartController?numQuantity=-1&id=${i.product.id}">-</a></button>
                            ${i.quantity}
                            <button><a href="UpdateCartController?numQuantity=1&id=${i.product.id}">+</a></button>  
                        </td>
                        <td> ${i.price} </td>
                        <td> ${i.price * i.quantity} </td>
                        <td> 
                            <form action="MainController" method="POST"> 
                                <input type="hidden" name="id" value="${i.product.id}"/>
                                <button type="submit" name="action" value="DeleteCart">    
                                    Remove
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        <h3>Total Money: ${o.totalMoney} Vnd</h3>   

        <form action="MainController" method="POST"  >
            <button type="submit" name="action" value="Display">    
                Click here to continue shopping
            </button>
        </form>  
        <br>
        <form action="MainController" method="POST"  >
            <input type="submit" name="action" value="Payment"/>                  
        </form>  
        <hr>
        <form action="MainController" method="POST"  >
            <button type="submit" name="action" value="CheckOut">    
                Check out
            </button>
        </form> 
    </center>
</body>
</html>