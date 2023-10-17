/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.DAO;
import model.User;

/**
 *
 * @author VietBao
 */
@WebServlet(name = "LoginAdController", urlPatterns = {"/LoginAdController"})
public class LoginAdController extends HttpServlet {

    private static final String ERROR = "loginAdmin.jsp";
    
    private static final String AD = "AD";
    private static final String US = "US";
    private static final String ADMIN_PAGE = "admin.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");    
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            String password = request.getParameter("password");                     
            DAO dao = new DAO();
            User loginUser = dao.checkLogin(id, password);
            if (loginUser != null) {
                String role_id = loginUser.getRole_id();
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);                           
                if (AD.equals(role_id)) {
                     url = ADMIN_PAGE;
                }else{                  
                    request.setAttribute("ERROR", "Your role cannot Login");                
                }
            } else {
                request.setAttribute("ERROR", "Incorrect userID or password");
            }
        } catch (Exception e) {
            log("Error at LoginCOntroller: " + e.toString());
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
