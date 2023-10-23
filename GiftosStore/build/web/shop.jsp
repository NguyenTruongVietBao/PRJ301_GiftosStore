<%-- 
    Document   : shop
    Created on : 14-10-2023, 21:53:00
    Author     : VietBao
--%>

<%@page import="model.User"%>
<%@page import="model.Category"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
                        <li class="nav-item active">
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
                        <form action="MainController" method="POST">
                            <i class="fa fa-shopping-bag" aria-hidden="true"></i>
                            <button style="border: none; background: none" type="submit" name="action" value="ViewCart">
                                Cart (${requestScope.sizeCart})
                            </button>
                        </form>  
                    </div>
                </div> 
                <div class="collapse navbar-collapse innerpage_navbar" id="navbarSupportedContent">
                    <ul class="navbar-nav" style="padding-right: 20px">
                        <form action="MainController" method="POST"  >
                            <li class="nav-item active">
                                <!--                                <a class="nav-link" href="MainController?action=Display">
                                                                    All
                                                                </a> -->
                                <button type="submit" name="action" value="Display" style="border: none; background: none">    
                                    <a class="nav-link" type="submit" name="action" value="Display">All</a>
                                </button>
                            </li>
                        </form>
                    </ul>
                    <ul class="navbar-nav" style="padding-right: 5px">
                        <li class="nav-item active">
                            <a class="nav-link" href="ProductController?category_id=Children">
                                Children
                            </a> 
                        </li>                           
                    </ul>
                    <ul class="navbar-nav" style="padding-right: 5px">
                        <li class="nav-item active">
                            <a class="nav-link" href="ProductController?category_id=Man">
                                Man
                            </a> 
                        </li>                           
                    </ul>
                    <ul class="navbar-nav" style="padding-right: 5px">
                        <li class="nav-item active">
                            <a class="nav-link" href="ProductController?category_id=Woman">
                                Woman
                            </a> 
                        </li>                           
                    </ul>
                    <form class="form-inline" action="MainController" method="POST" style="padding-left: 150px"  >
                        <input type="text" name="searchCart" value="<%= search%>" placeholder="What are u looking for ?"/>
                        <button class="btn nav_search-btn" type="submit" name="action" value="SearchCart">    
                            &nbsp;
                            <i class="fa fa-search" aria-hidden="true"></i>
                            <span>Search</span>
                        </button>
                    </form>
                </div>
            </nav>
        </header>
        <!-- end header section -->
    </div>
    <!-- end hero area -->

    <!-- shop section -->
    <section class="shop_section layout_padding">
        <div class="container">           
            <div class="heading_container heading_center">
                <h1>All product</h1>
            </div>
            <div class="row"> 
                <c:forEach items="${requestScope.LIST_PRODUCT_BY_CID}" var="p"> 
                    <div class="col-sm-6 col-md-4 col-lg-3">
                        <div class="box">
                            <div class="img-box">
                                <img src="${p.thumbnail}">
                            </div>
                            <div class="detail-box">
                                <h6>    ${p.title}  </h6>
                                <h6>    <span>  ${p.price}$ </span> </h6>
                            </div>                                            
                            <div class="new">
                                <span>  ${p.id} </span>
                            </div>                                       
                            <div>
                                <span style="color: #007bff">SL: ${p.quantity}</span>
                                <!--<input type="hidden" name="numQuantity" value="1"/>-->
                                <a href="AddCartController?id=${p.id}&numQuantity=1" >
                                    <button style="background-color: orange; color:white; border:none;padding: 0 10px; margin-left: 77px">
                                        Add to Cart
                                    </button>
                                </a>   
                            </div>
                        </div>                            
                    </div>                               
                </c:forEach>  
            </div>           
            <div class="row"> 
                <c:if test="${LIST_PRODUCT_BY_CID == null}">
                    <c:forEach items="${requestScope.LIST_PRODUCT}" var="p2">
                        <div class="col-sm-6 col-md-4 col-lg-3">
                            <div class="box">
                                <div class="img-box">
                                    <img src="${p2.thumbnail}">
                                </div>
                                <div class="detail-box">
                                    <h6>
                                        ${p2.title}
                                    </h6>
                                    <h6>
                                        <span>
                                            ${p2.price}$
                                        </span>
                                    </h6>
                                </div>                                            
                                <div class="new">
                                    <span>
                                        ${p2.id}
                                    </span>
                                </div>                                    
                                <div>
                                    <span style="color: #007bff">
                                        SL: ${p2.quantity}
                                    </span>
                                    <a href="AddCartController?id=${p2.id}&numQuantity=1" >
                                        <button style="background-color: orange; color:white; border:none;padding: 0 10px; margin-left: 77px">
                                            Add to Cart
                                        </button>
                                    </a>   
                                </div>
                            </div>                            
                        </div> 
                    </c:forEach>
                </c:if>
            </div>     
            <div class="row">
                <%  List<Product> listSearchProduct = (List<Product>) request.getAttribute("LIST_SEARCHPRODUCT");
                    if (listSearchProduct != null) {
                        if (listSearchProduct.size() > 0) {
                            for (Product searchProduct : listSearchProduct) {
                %>
                <div class="col-sm-6 col-md-4 col-lg-3">
                    <div class="box">
                        <div class="img-box">
                            <img src=" <%= searchProduct.getThumbnail()%>" alt="">
                        </div>
                        <div class="detail-box">
                            <h6>
                                <%= searchProduct.getTitle()%>
                            </h6>
                            <h6>
                                <span>
                                    <%= searchProduct.getPrice()%>$
                                </span>
                            </h6>
                        </div>                                            
                        <div class="new">
                            <span>
                                <%= searchProduct.getId()%>
                            </span>
                        </div>                                                              
                        <div>
                            <span style="color: #007bff">SL: <%= searchProduct.getQuantity()%></span>
                            <a href="AddCartController?id=<%= searchProduct.getId()%>&numQuantity=1" >
                                <button style="background-color: orange; color:white; border:none;padding: 0 10px; margin-left: 77px">
                                    Add to Cart
                                </button>
                            </a>   
                        </div>                        
                    </div>                             
                </div>                       
                <%
                            }
                        }
                    }
                %>  
            </div>
        </div>
    </section>
    <!-- end shop section -->

    <!-- info section -->
    <section class="info_section  layout_padding2-top">
        <div class="social_container">
            <div class="social_box">
                <a href="">
                    <i class="fa fa-facebook" aria-hidden="true"></i>
                </a>
                <a href="">
                    <i class="fa fa-twitter" aria-hidden="true"></i>
                </a>
                <a href="">
                    <i class="fa fa-instagram" aria-hidden="true"></i>
                </a>
                <a href="">
                    <i class="fa fa-youtube" aria-hidden="true"></i>
                </a>
            </div>
        </div>

        <div class="info_container ">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-lg-3">
                        <h6>
                            ABOUT US
                        </h6>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed doLorem ipsum dolor sit amet, consectetur adipiscing elit, sed doLorem ipsum dolor sit amet,
                        </p>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <div class="info_form ">
                            <h5>
                                Newsletter
                            </h5>
                            <form action="#">
                                <input type="email" placeholder="Enter your email">
                                <button>
                                    Subscribe
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <h6>
                            NEED HELP
                        </h6>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed doLorem ipsum dolor sit amet, consectetur adipiscing elit, sed doLorem ipsum dolor sit amet,
                        </p>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <h6>
                            CONTACT US
                        </h6>
                        <div class="info_link-box">
                            <a href="">
                                <i class="fa fa-map-marker" aria-hidden="true"></i>
                                <span> Gb road 123 london Uk </span>
                            </a>
                            <a href="">
                                <i class="fa fa-phone" aria-hidden="true"></i>
                                <span>+01 12345678901</span>
                            </a>
                            <a href="">
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                                <span> demo@gmail.com</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- footer section -->
        <footer class=" footer_section">
            <div class="container">
                <p>
                    &copy; <span id="displayYear"></span> All Rights Reserved By
                    <a href="https://html.design/">Free Html Templates</a>
                </p>
            </div>
        </footer>
        <!-- footer section -->
    </section>
    <!-- end info section -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
    </script>
    <script src="js/custom.js"></script>
</body>
</html>
