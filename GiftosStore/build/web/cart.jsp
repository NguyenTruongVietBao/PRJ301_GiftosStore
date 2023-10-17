<%-- 
    Document   : cart
    Created on : 15-10-2023, 22:43:12
    Author     : VietBao
--%>

<%@page import="model.Product"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Your Giftos</h1>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart != null){
        %>     
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Actions</th>
                    <th><a href="shopping.jsp">AddCart</a></th>
                </tr>
            </thead>
            <tbody>
                <%
                    double total = 0;
                    int count = 1;
                    for (Product product : cart.getCart().values()) {   
                        total = total + product.getPrice() * product.getQuantity();
                %>
                <form action="MainController">
                    <tr>
                        <td><%= count++ %></td>
                        <td>
                            <input type="text" name="id" value="<%= product.getId() %>" readonly="" >
                        </td>
                        <td><%= product.getTitle()%></td>
                        <td><%= product.getPrice() %></td>
                        <td>
                            <input type="number" name="cmbQuantity" value="<%= product.getQuantity() %>" >
                        </td>
                        <td><%= product.getPrice() * product.getQuantity() %></td>
                        <td> 
                            <input type="submit" name="action" value="UpdateCart"/>
                            <input type="hidden" name="id" value="<%=product.getId()%>"/>
                            <input type="submit" name="action" value="DeleteCart"/>                            
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
            </tbody>
        </table>
        <h1>Total: <%= total %></h1>
        <%
            }else{
        %>
        <h2>Ban chua co sp nao</h2>
        <%
            }
        %>
    </body>
</html>