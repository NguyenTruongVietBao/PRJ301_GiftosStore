/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VietBao
 */
public class MainController extends HttpServlet {
    private static final String WELCOME = "home.jsp";
//      User
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGINAD = "LoginAd";
    private static final String LOGINAD_CONTROLLER = "LoginAdController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";     
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String INSERT = "Insert";
    private static final String INSERT_CONTROLLER = "InsertController";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";
//      Shopping Cart
    private static final String DISPLAY = "Display";
    private static final String DISPLAY_CONTROLLER = "DisplayController";
    private static final String VIEWCART = "Cart";
    private static final String VIEWCART_CONTROLLER = "cart.jsp";
    private static final String ADDCART = "AddCart";
    private static final String ADDCART_CONTROLLER = "AddCartController";
    private static final String SEARCHCART = "SearchCart";
    private static final String SEARCHCART_CONTROLLER = "SearchCartController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            
            if (action == null) {
                url = WELCOME;
            } 
//User           
            else if (LOGIN.equals(action))      { url = LOGIN_CONTROLLER; }
            else if (LOGOUT.equals(action))     { url = LOGOUT_CONTROLLER; }
            else if (LOGINAD.equals(action))    { url = LOGINAD_CONTROLLER; }
            else if (SEARCH.equals(action))     { url = SEARCH_CONTROLLER; }
            else if (INSERT.equals(action))     { url = INSERT_CONTROLLER; }
            else if (UPDATE.equals(action))     { url = UPDATE_CONTROLLER; }
            else if (DELETE.equals(action))     { url = DELETE_CONTROLLER; }
                       
//Shopping            
            else if (DISPLAY.equals(action))    { url = DISPLAY_CONTROLLER; }           
            else if (VIEWCART.equals(action))   { url = VIEWCART_CONTROLLER; }   
            else if (ADDCART.equals(action))    { url = ADDCART_CONTROLLER; }
            else if (SEARCHCART.equals(action)) { url = SEARCHCART_CONTROLLER; }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
