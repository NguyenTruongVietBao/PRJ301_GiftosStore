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

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <style>
            body{margin-top:20px;
                 background:#ddd;
            }

            .link-p {
                display: block;
                min-height: 250px;
                height: auto;
                width: 100%;
                max-width: 100%;
                margin-right: auto;
                margin-left: auto;
            }

            /* TABLES */
            .table {
                border-collapse: separate;
            }
            .table-hover > tbody > tr:hover > td,
            .table-hover > tbody > tr:hover > th {
                background-color: #eee;
            }
            .table thead > tr > th {
                border-bottom: 1px solid #C2C2C2;
                padding-bottom: 0;
            }
            .table tbody > tr > td {
                font-size: 0.875em;
                background: #f5f5f5;
                border-top: 10px solid #fff;
                vertical-align: middle;
                padding: 12px 8px;
            }
            .table tbody > tr > td:first-child,
            .table thead > tr > th:first-child {
                padding-left: 20px;
            }
            .table thead > tr > th span {
                border-bottom: 2px solid #C2C2C2;
                display: inline-block;
                padding: 0 5px;
                padding-bottom: 5px;
                font-weight: normal;
            }
            .table thead > tr > th > a span {
                color: #344644;
            }
            .table thead > tr > th > a span:after {
                content: "\f0dc";
                font-family: FontAwesome;
                font-style: normal;
                font-weight: normal;
                text-decoration: inherit;
                margin-left: 5px;
                font-size: 0.75em;
            }
            .table thead > tr > th > a.asc span:after {
                content: "\f0dd";
            }
            .table thead > tr > th > a.desc span:after {
                content: "\f0de";
            }
            .table thead > tr > th > a:hover span {
                text-decoration: none;
                color: #2bb6a3;
                border-color: #2bb6a3;
            }
            .table.table-hover tbody > tr > td {
                -webkit-transition: background-color 0.15s ease-in-out 0s;
                transition: background-color 0.15s ease-in-out 0s;
            }
            .table tbody tr td .call-type {
                display: block;
                font-size: 0.75em;
                text-align: center;
            }
            .table tbody tr td .first-line {
                line-height: 1.5;
                font-weight: 400;
                font-size: 1.125em;
            }
            .table tbody tr td .first-line span {
                font-size: 0.875em;
                color: #969696;
                font-weight: 300;
            }
            .table tbody tr td .second-line {
                font-size: 0.875em;
                line-height: 1.2;
            }
            .table a.table-link {
                margin: 0 5px;
                font-size: 1.125em;
            }
            .table a.table-link:hover {
                text-decoration: none;
                color: #2aa493;
            }
            .table a.table-link.danger {
                color: #fe635f;
            }
            .table a.table-link.danger:hover {
                color: #dd504c;
            }

            .table-products tbody > tr > td {
                background: none;
                border: none;
                border-bottom: 1px solid #ebebeb;
                -webkit-transition: background-color 0.15s ease-in-out 0s;
                transition: background-color 0.15s ease-in-out 0s;
                position: relative;
            }
            .table-products tbody > tr:hover > td {
                text-decoration: none;
                background-color: #f6f6f6;
            }
            .table-products .name {
                display: block;
                font-weight: 600;
                padding-bottom: 7px;
            }
            .table-products .price {
                display: block;
                text-decoration: none;
                width: 50%;
                float: left;
                font-size: 0.875em;
            }
            .table-products .price > i {
                color: #8dc859;
            }
            .table-products .warranty {
                display: block;
                text-decoration: none;
                width: 50%;
                float: left;
                font-size: 0.875em;
            }
            .table-products .warranty > i {
                color: #f1c40f;
            }
            .table tbody > tr.table-line-fb > td {
                background-color: #9daccb;
                color: #262525;
            }
            .table tbody > tr.table-line-twitter > td {
                background-color: #9fccff;
                color: #262525;
            }
            .table tbody > tr.table-line-plus > td {
                background-color: #eea59c;
                color: #262525;
            }
            .table-stats .status-social-icon {
                font-size: 1.9em;
                vertical-align: bottom;
            }
            .table-stats .table-line-fb .status-social-icon {
                color: #556484;
            }
            .table-stats .table-line-twitter .status-social-icon {
                color: #5885b8;
            }
            .table-stats .table-line-plus .status-social-icon {
                color: #a75d54;
            }
        </style>
    </head>
    <body>  
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="container bootdey">
            <div class="row bootstrap snippets">
                <div class="col-lg-3 col-md-3 col-sm-12">
                <!-- Best Seller -->
                <div class="col-lg-12 col-md-12 col-sm-12 hidden-xs">
                    <div class="no-padding">
                        <span class="title">BEST SELLER</span>
                    </div>
                    <div class="thumbnail col-lg-12 col-md-12 col-sm-6 text-center">
                        <a href="#" class="link-p" style="overflow: hidden; position: relative;">
                            <img src="https://www.bootdey.com/image/700x500/FF69B4/000000" alt="" >
                        </a>
                        <div class="caption prod-caption">
                            <h4><a href="#">Penn State College T-Shirt</a></h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut, minima!</p>
                            <p>
                            </p><div class="btn-group">
                                <a href="#" class="btn btn-default">$ 528.96</a>
                                <a href="#" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Buy</a>
                            </div>
                            <p></p>
                        </div>
                    </div>
                    <div class="thumbnail col-lg-12 col-md-12 col-sm-6 visible-sm text-center">
                        <a href="#" class="link-p" style="overflow: hidden; position: relative;">
                            <img src="https://www.bootdey.com/image/700x500/00CED1/000000">
                        </a>
                        <div class="caption prod-caption">
                            <h4><a href="#">Ohio State College T-Shirt</a></h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut, minima!</p>
                            <p>
                            </p><div class="btn-group">
                                <a href="#" class="btn btn-default">$ 924.25</a>
                                <a href="#" class="btn btn-primary"><i class="fa fa-shopping-cart"></i> Buy</a>
                            </div>
                            <p></p>
                        </div>
                    </div>
                </div>
                <!-- End Best Seller -->
                </div>
                
                <div class="clearfix visible-sm"></div>
                
                <!-- Cart -->
                <div class="col-lg-9 col-md-9 col-sm-12">
                    <div class="col-lg-12 col-sm-12">
                        <span class="title">SHOPPING CART</span>
                    </div>
                    <div class="col-lg-12 col-sm-12 hero-feature">
                        <div class="table-responsive">
                            <table class="table table-bordered tbl-cart">
                                <thead>
                                    <tr>
                                        <td> No </td>
                                        <td class="hidden-xs">Image</td>
                                        <td>Product Name</td>
                                        <td class="td-qty">Quantity</td>
                                        <td>Unit Price</td>
                                        <td>Total Price</td>
                                        <td>Remove</td>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:set var="o" value="${requestScope.Cart}"/>
                                    <c:set var="stt" value="0"/>
                                    <c:forEach items="${o.items}" var="i">
                                        <c:set var="stt" value="${stt+1}"/>
                                        <tr>
                                            <td> ${stt} </td>
                                            <td class="hidden-xs">                                        
                                                <img src="${i.product.thumbnail}" width="50" height="50">                                      
                                            </td>
                                            <td>${i.product.title}</td>                                    
                                            <td>
                                                <div class="input-group bootstrap-touchspin">
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default bootstrap-touchspin-down" type="button">
                                                            <a href="UpdateCartController?numQuantity=-1&id=${i.product.id}">-</a>
                                                        </button>
                                                    </span>
                                                    <span class="input-group-addon bootstrap-touchspin-prefix" style="display: none;">

                                                    </span>
                                                    <input type="text" name="" value="${i.quantity}" class="input-qty form-control text-center" style="display: block;">
                                                    <span class="input-group-addon bootstrap-touchspin-postfix" style="display: none;">

                                                    </span>
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default bootstrap-touchspin-up" type="button">
                                                            <a href="UpdateCartController?numQuantity=1&id=${i.product.id}">+</a>
                                                        </button>
                                                    </span>
                                                </div>
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
<!--                                            <td class="text-center">
                                                <form action="MainController" method="POST"> 
                                                    <input type="hidden" name="id" value="${i.product.id}"/>
                                                    <button class="remove_cart" type="submit" name="action" value="DeleteCart">    
                                                        <i class="fa fa-trash-o"></i>
                                                    </button>
                                                </form>

                                            </td>-->
                                        </tr> 
                                    </c:forEach>                                                                
                                    <tr>
                                        <td colspan="6" align="right">Total</td>
                                        <td class="total" colspan="2"><b> ${o.totalMoney}</b>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="btn-group btns-cart">
                            <button type="button" class="btn btn-primary"><i class="fa fa-arrow-circle-left"></i> Continue Shopping</button>
                            <button type="button" class="btn btn-primary">Checkout <i class="fa fa-arrow-circle-right"></i></button>
                        </div>

                    </div>
                </div>
                <!-- End Cart -->
            </div>
        </div>
    </body>
</html>