/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cart;

import DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Product;
import model.User;

/**
 *
 * @author VietBao
 */
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

        private static final String ERROR = "a.jsp";    
        private static final String SUCCESS = "orderSuccess.jsp";    

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            DAO dao = new DAO();
            List<Product> list = dao.getAllProduct();
            Cookie arr[] = request.getCookies();
            String txt="";      
            if(arr != null){
                for (Cookie cookie : arr) {
                    if(cookie.getName().equals("Cart")){    
                        txt += cookie.getValue();
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            Cart cart = new Cart(txt, list);
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("LOGIN_USER");
            if(u == null){
                response.sendRedirect("login.jsp");
            }else{               
                dao.addOrder(u, cart);
                Cookie c = new Cookie("Cart", txt);
                c.setMaxAge(0);
                response.addCookie(c); 
                url = SUCCESS;
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            
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
